### Migration to SpringBoot2.0
In this branch we have migrated the spring app from 1.5 to 2.0
We have added ApplicationConfig class to start the h2 database in browseable mode
### Add Correlation Id in message (AddCorrelationIdInMessage)
In this branch we are adding Servlet Filter, so we could inject the correlation id in request and 
response