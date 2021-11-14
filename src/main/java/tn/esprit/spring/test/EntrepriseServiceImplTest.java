package tn.esprit.spring.test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Optional;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
	
	
	 
	@Mock
	@Autowired
	private EmployeRepository empR;
	@Mock
	@Autowired
	private DepartementRepository depR;
	@Mock
	@Autowired
	private EntrepriseRepository entR;
	@Autowired
	@InjectMocks
	private EntrepriseServiceImpl entrS;
	@Mock
	@Autowired
	private IEntrepriseService etrS;
	private Entreprise e;
	private Departement d;
	
	
	@Before
	public void setUp() {
		e = entR.save(new Entreprise("Blue1", "Unity"));
		d = depR.save(new Departement("GRH"));
		d.setEntreprise(e);
		depR.save(d);
	}
	
	@After
	public void tearDown() {
		entR.deleteAll();
		depR.deleteAll();
		
	}
	
	@Test
	public void testAjouterDepartement() {
		Departement dept = new Departement("TIC");
		int AddedepID = entrS.ajouterDepartement(dept);
		Optional<Departement> departement = depR.findById(AddedepID);
		if(departement.isPresent()) {
			String name= departement.get().getName();
			assertEquals("TIC", name);
		}
	}
	
	@Test
	public void  testajouterEntreprise(){
		int id = etrS.ajouterEntreprise(new Entreprise("entreprise1","entreprise1")); 
	    assertTrue("ajout Entreprise echoué",entR.findById(id).isPresent());
	}
}
