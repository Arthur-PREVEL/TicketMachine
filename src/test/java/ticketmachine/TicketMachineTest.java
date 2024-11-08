package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}
	@Test
	// S3 : on n'imprime pas le ticket si le montant inséré est insuffisant
	void nImprimePasSiBalanceInsuffisante() {
		//GIVEN : une machine vierge
		//WHEN : On ne met pas assez d'argent
		machine.insertMoney( PRICE -1);
		//THEN : ça n'imprime pas
		assertFalse(machine.printTicket(), "Pas assez d'argent, on ne doit pas imprimer");
	}

	@Test
	// S4 : on imprime le ticket si le montant inséré est suffisant
	void imprimeTicketSiBalanceSuffisante(){
		//GIVEN : une machine vierge
		//WHEN : On met assez d'argent
		machine.insertMoney( PRICE );
		//THEN : ça imprime
		assertTrue(machine.printTicket(), "N'imprime pas le ticket, bien qu'il y ait assez d'argent");
	}

	@Test
	// S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void decrementeBalanceQuandTicketImprime() {
		//GIVEN : une machine vierge
		//WHEN : On imprime ticket
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		//THEN : la balance a été décrémenté du prix du ticket
		assertEquals(10, machine.getBalance(), "la balance n'a pas été décrémentée");
	}
	@Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void montantCollecteEstMisAJourApresImpression(){
		//GIVEN : une machine vierge
		// WHEN : On imprime ticket
		machine.insertMoney( PRICE + 10 );
		machine.printTicket();
		//THEN : le montant collecté est mis à jour
		assertEquals( PRICE, machine.getTotal(), "le montant collecté n'a pas été mis à jour");
		}

	@Test
		// S7 : refund() rend correctement la monnaie
	void refundRendCorrectementMonnaie() {
		//GIVEN : une machine vierge
		// WHEN : On rend la monnaie
		machine.insertMoney( PRICE + 20 );
		machine.refund();
		//THEN : le client reçoit la bonne somme
		assertEquals( PRICE + 20, 70,"refund() ne rend pas correctement la monnaie" );
	}

	@Test
		//S8 : refund() remet la balance à zéro
	void refundRemetBalanceAZero() {
		//GIVEN : une machine vierge
		// WHEN : On rend la monnaie
		machine.insertMoney( PRICE + 20 );
		machine.refund();
		// THEN : Balance est mis à jour à 0
		assertEquals( 0, machine.getBalance(), "refund() ne remet pas la balance à zéro" );
		}

	@Test
		//S9 : on ne peut pas insérer un montant négatif

	void pasDeMontantNegatif() {
		//GIVEN : une machine vierge
		//WHEN : on insère montant négatif
		//THEN : l'opération n'est pas acceptée
		try {
			machine.insertMoney( -1 );
			fail("Cet appel doit lever une exception");
		}
		catch (IllegalArgumentException e){
		}

	}
	@Test

		//S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif

	void pasDePrixTicketNegatif() {
		//GIVEN : -
		//WHEN : on créait une machine avec un prix de ticket négatif
		//THEN : l'opération n'est pas acceptée
		try {
			TicketMachine machine2 = new TicketMachine(-239);
			fail("Cet appel doit lever une exception");
		}
		catch (IllegalArgumentException e){
		}
	}



	}








