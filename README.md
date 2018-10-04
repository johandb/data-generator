# data-generator
Create random data for java class

You can generate data for a class like this

DataGenerator r = new DataGenerator.DataGeneratorBuilder().build();
Company company = r.random(Company.class);

