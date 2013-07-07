package Degiro;

import Degiro.json.JsonPortfolio;
import Degiro.json.Value_;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.security.*;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DegiroConnect {

    final static String USER_CREDENTIALS_FILENAME_DEFAULT = "../secure/degiro.txt";

    private Log log = LogFactory.getLog(DegiroConnect.class);

    public DegiroConnect(Credentials _credentials)
    {
        credentials = _credentials;
    }

    private HttpClient http;

    private static HttpClient trustEveryoneSslHttpClient() {
        try {
            SchemeRegistry registry = new SchemeRegistry();

            SSLSocketFactory socketFactory = new SSLSocketFactory(new TrustStrategy() {

                public boolean isTrusted(final X509Certificate[] chain, String authType) {
                    // Oh, I am easy...
                    return true;
                }

            }, org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            registry.register(new Scheme("https", 443, socketFactory));
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
            DefaultHttpClient client = new DefaultHttpClient(mgr, new DefaultHttpClient().getParams());
            return client;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public org.apache.http.client.HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);


            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            DefaultHttpClient newClient = new DefaultHttpClient(ccm, params);

            cookieStore = new BasicCookieStore();
            // Create local HTTP context
            localContext = new BasicHttpContext();
            // Bind custom cookie store to the local context
            localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

            newClient.setCookieStore(cookieStore);
            return newClient;
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    CookieStore cookieStore;
    HttpContext localContext;

    public void login()
    {
        client =getNewHttpClient();
        client.getParams().setParameter(
                ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
        HttpPost httppost = new HttpPost("https://www.degiro.nl/secure/j_spring_security_check");
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("j_username", credentials.getUsername()));
        postParameters.add(new BasicNameValuePair("j_password", credentials.getPassword()));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(postParameters));
            //HttpResponse response =
            client.execute(httppost,localContext);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static String slurp(final InputStream is, final int bufferSize)
    {
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try {
            final Reader in = new InputStreamReader(is, "UTF-8");
            try {
                for (;;) {
                    int rsz = in.read(buffer, 0, buffer.length);
                    if (rsz < 0)
                        break;
                    out.append(buffer, 0, rsz);
                }
            }
            finally {
                in.close();
            }
        }
        catch (IOException ex)
        {
            throw new RuntimeException("DegiroConnct.slurp failed:"+ex.getMessage());
        }
        return out.toString();
    }

    public Portfolio getPortfolio() {
        Date now = new Date();
        String sTimestamp =  new SimpleDateFormat("yyyy-MM-dd").format(now);
        sTimestamp += "T" + new SimpleDateFormat("hh:mm:ss").format(now)+ "Z";
        String message = sTimestamp;

        HttpGet httpget= new HttpGet("https://www.degiro.nl/secure/v3/update?portfolio=0&orders=0&transactions=0&totalPortfolio=0");

        BasicHttpParams params=new BasicHttpParams();
        params.setParameter("uniqueid", message);

        try {
            httpget.setParams(params);
            HttpResponse response = client.execute(httpget,localContext);
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            //domFactory.setNamespaceAware(true);
            String result = slurp(response.getEntity().getContent(),1024);
            //System.out.println(result);
            ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

            JsonPortfolio jsonPortfolio;
            jsonPortfolio = mapper.readValue(result, JsonPortfolio.class);
            Portfolio p = new Portfolio(jsonPortfolio);
            return p;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("DegiroConnect.getPortfolio: "+e.getMessage());
        }
    }


    static final int BUY = 0;
    static final int SELL = 1;
    static final int ACTION_DEFAULT = 0;
    static final int ORDERTYPE_DEFAULT = 0;
    static final int PRICETYPE_DEFAULT = 1;
    static final int TIMETYPE_GTD = 1;


    public void sendOrder(final Order order)
    {

        login();
        HttpPost httppost = new HttpPost("https://www.degiro.nl/secure/order/send");
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("productId", Integer.toString(order.getPid())));
        postParameters.add(new BasicNameValuePair("action", Integer.toString(order.getAction())));
        postParameters.add(new BasicNameValuePair("buysell", Integer.toString(order.getBuysell())));
        postParameters.add(new BasicNameValuePair("size", Integer.toString(order.getSize())));
        postParameters.add(new BasicNameValuePair("price", Double.toString(order.getSize())));
        postParameters.add(new BasicNameValuePair("orderType", Integer.toString(order.getOrdertype())));
        postParameters.add(new BasicNameValuePair("priceType", Integer.toString(order.getPricetype())));
        postParameters.add(new BasicNameValuePair("timeType", Integer.toString(order.getTimetype())));
        postParameters.add(new BasicNameValuePair("disclosedSize", Integer.toString(order.getDisclosedsize())));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(postParameters));
            HttpResponse response = client.execute(httppost,localContext);
            System.out.println(response.toString());
            return;
        } catch (IOException e) {
            throw new RuntimeException("DegiroConnect.sendOrder: "+ e.getMessage());
        }
    }

    Credentials credentials;
    HttpClient client;

}


