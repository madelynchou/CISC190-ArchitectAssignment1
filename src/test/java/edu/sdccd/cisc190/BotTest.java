package edu.sdccd.cisc190;

import edu.sdccd.cisc190.players.bots.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    @Test
    void isAnitaMaxWynnInstanceOfBots() {
        AnitaMaxWynn anitaMaxWynn = AnitaMaxWynn.getInstance();

        //determine if specific bot is a child of Bot class
        assertInstanceOf(Bot.class, anitaMaxWynn);

        //assert that the values of the attributes are assigned correctly
        assertEquals("Anita Max Wynn", anitaMaxWynn.getName());
        assertEquals(1000, anitaMaxWynn.getMoney());
        assertEquals(0.8, anitaMaxWynn.getLuck());
        assertEquals(0.3, anitaMaxWynn.getAura());
    }

    @Test
    void isChaseInstanceOfBots() {
        Chase chase = Chase.getInstance();

        assertInstanceOf(Bot.class, chase);

        assertEquals("Chase Allan", chase.getName());
        assertEquals(1000, chase.getMoney());
        assertEquals(0.25, chase.getLuck());
        assertEquals(0.1, chase.getAura());
    }

    @Test
    void isHondaBoyzInstanceOfBots() {
        HondaBoyz hondaBoyz = HondaBoyz.getInstance();

        assertInstanceOf(Bot.class, hondaBoyz);

        assertEquals("HondaBoyz", hondaBoyz.getName());
        assertEquals(1000, hondaBoyz.getMoney());
        assertEquals(1.0, hondaBoyz.getLuck());
        assertEquals(0.1, hondaBoyz.getAura());
    }

    @Test
    void isMrBrooksInstanceOfBots() {
        MrBrooks mrBrooks = MrBrooks.getInstance();

        assertInstanceOf(Bot.class, mrBrooks);

        assertEquals("MrBrooks", mrBrooks.getName());
        assertEquals(1000, mrBrooks.getMoney());
        assertEquals(0.5, mrBrooks.getLuck());
        assertEquals(0.7, mrBrooks.getAura());
    }

    @Test
    void isProfessorHuangInstanceOfBots() {
        ProfessorHuang professorHuang = ProfessorHuang.getInstance();

        assertInstanceOf(Bot.class, professorHuang);

        assertEquals("Professor Huang", professorHuang.getName());
        assertEquals(1000, professorHuang.getMoney());
        assertEquals(0.95, professorHuang.getLuck());
        assertEquals(0.6, professorHuang.getAura());
    }
}