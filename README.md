DegiroAPI
=========

API for the degiro.nl brokerage

available features (in Java):
- reading portfolio from degiro.nl for a given account
- sending new orders for a given productId

planned (in Java):
- order management to keep track of open orders
- canceling open orders
- modifying open orders
- receiving latest price for a given productId

Usage:

Before running the examples add the file
../secure/degiro.txt
consisting of
username=<username>
password=<password>

These credentials you receive when opening an account at degiro.nl

To send an order you need the productId of a financial instrument. Use the product finder of the web interface of degiro.nl to find productIds.



Looking for developers to translate the API to C# and other languages
