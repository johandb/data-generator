# data-generator
Create random data for java class

# You can generate data for a class like this

DataGenerator r = new DataGenerator.DataGeneratorBuilder().build();<br>
Company company = r.random(Company.class);

# If you want create a list of 5 companies with random data
List<Company> companies = r.randomList(Company.class, 5);

# If you want to exclude fields do it like this<br>
DataGenerator generator = new DataGenerator.DataGeneratorBuilder().withExcludeField("name", "dateBirth").build();

# See the GeneratorParameters class for setting more options.
