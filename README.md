# data-generator
Create random data for java class

You can generate data for a class like this

DataGenerator r = new DataGenerator.DataGeneratorBuilder().build();<br>
Company company = r.random(Company.class);

If you want to exclude fields do it like this
DataGenerator generator = new DataGenerator.DataGeneratorBuilder().withExcludeField("name", "dateBirth").build();
