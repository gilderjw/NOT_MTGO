package devops.hw1.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import back_end.AbilityType;
import back_end.SMELParser;
import back_end.Zone;

public class SMELParserTest {

	@Test
	public void testGetCost() {
		String smel = "COST {7U}";
		SMELParser parse = new SMELParser(smel);

		assertEquals(parse.getCost(), "7U");

		smel = "COST {B}";
		parse = new SMELParser(smel);

		assertEquals("B", parse.getCost());

		smel = "";
		parse = new SMELParser(smel);

		try{
			parse.getCost();
			fail("expected exception");
		} catch (RuntimeException e){
			assertEquals(e.getMessage(), "SMEL: no cost");
		}


		try{
			parse = new SMELParser(null);
			fail("expected exception");
		} catch (RuntimeException e){
			assertEquals(e.getMessage(), "SMEL: no cost");
		}
	}

	@Test
	public void testCostEfectParse(){
		String LlanowarElves = "COST { TAP } EFFECT { MANA G}";
		SMELParser parse = new SMELParser(LlanowarElves);

		assertEquals("TAP", parse.getCost());
		assertEquals("MANAG", parse.getEffect());
	}

	@Test
	public void testTarget(){
		String giantGrowth = "COST { G } EFFECT { POWER += 3, TOUGHNESS += 3} TARGET { CREATURE }";
		SMELParser parse = new SMELParser(giantGrowth);

		assertEquals("CREATURE", parse.getTarget());

	}

	@Test
	public void testZones(){
		String giantGrowth = "COST { G } EFFECT { POWER += 3, TOUGHNESS += 3} TARGET { CREATURE } ZONE { HAND } RESOLVE { GRAVEYARD }";
		SMELParser parse = new SMELParser(giantGrowth);

		assertEquals(Zone.HAND, parse.getZone());
		assertEquals(Zone.GRAVEYARD, parse.getResolveZone());

	}

	@Test
	public void testType(){
		String giantGrowth = "COST { G } EFFECT { POWER += 3, TOUGHNESS += 3} TARGET { CREATURE } ZONE { HAND } RESOLVE { GRAVEYARD } TYPE { CAST }";
		SMELParser parse = new SMELParser(giantGrowth);

		assertEquals(AbilityType.CAST, parse.getType());
	}
}
