--SELECT * FROM customers
--WHERE Country != 'USA';

--SELECT * FROM customers 
--WHERE Country = 'Germany';

--SELECT * FROM employees
--WHERE Title = 'Sales Support Agent';
 
SELECT DISTINCT BillingCountry 
FROM invoices;


--SELECT * FROM tracks t 
--ORDER BY Name DESC
--LIMIT 25
--OFFSET 10;