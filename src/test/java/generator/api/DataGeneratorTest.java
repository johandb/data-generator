package generator.api;

import generator.model.Company;
import generator.model.Person;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataGeneratorTest {

    @Test
    public void testPersonDataGenerator() throws Exception {
        final DataGenerator r = new DataGenerator.DataGeneratorBuilder().build();
        final Person p = r.random(Person.class);
        r.reset();
        final Person p1 = r.random(Person.class);
        assertNotNull(p);
        assertNotNull(p1);
    }

    @Test
    public void testCompanyData() throws Exception {
        final DataGenerator r = new DataGenerator.DataGeneratorBuilder().build();
        final Company company = r.random(Company.class);
        assertNotNull(company);
    }

    @Test
    public void testPersonDataGeneratorToJson() throws Exception {
        final DataGenerator r = new DataGenerator.DataGeneratorBuilder().withExcludeField("person").build();
        final Person p = r.random(Person.class);
        assertNotNull(p);
        r.reset();
        final String s = r.randomAsJson(Person.class);
        assertTrue(!s.isEmpty());
    }

    @Test
    public void testPersonDataGeneratorWithExcludingFields() {
        final DataGenerator generator = new DataGenerator.DataGeneratorBuilder().withExcludeField("name", "dateBirth").build();
        final Person p = generator.random(Person.class);
        assertNotNull(p);
        assertNull(p.getName());
        assertNull(p.getDateBirth());
    }

    @Test
    public void testCreateRandomDataList() {
        final DataGenerator r = new DataGenerator.DataGeneratorBuilder().build();
        List<Person> persons = r.randomList(Person.class, 5);
        assertTrue(persons.size() == 5);
    }
}
