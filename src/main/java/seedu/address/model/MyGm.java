package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;
import seedu.address.model.team.Team;
import seedu.address.model.lineup.Lineup;

/**
 * Represents the root class of MyGM.
 */
public class MyGm {
    private final UniquePlayerList players;
    private final List<Team> teams;

    /**
     * Creates a new empty MyGm class.
     */
    public MyGm() {
        this.players = new UniquePlayerList();
        this.teams = new ArrayList<Team>();
    }

    // add more methods here to facilitate update of players and teams

    /**
     * Gets the list of lineups a player belongs to.
     */
    public List<Lineup> getPlayerLineup(Person player) {
        return this.players.getPlayerLineup(player);
    }

    /**
     * Adds a person to a team and update all players' information.
     */
    public void putPersonToTeam(Person person, Team team) {
        this.players.addPersonToTeam(person, team);
    }

    /**
     * Adds a person to a lineup and update all players' information.
     */
    public void putPersonToLineup(Person person, Lineup lineup) {
        this.players.addPersonToLineup(person, lineup);
    }

    /**
     * Removes a person and update all players' information.
     */
    public void removePerson(Person person) {
        this.players.removePerson(person);
    }

    /**
     * Removes a person from a team and update all players' information.
     */
    public void removePersonFromTeam(Person person, Team team) {
        this.players.removePersonFromTeam(person, team);
    }

    /**
     * Removes a person from a lineup and update all players' information.
     */
    public void removePersonFromLineup(Person person, Lineup lineup) {
        this.players.removePersonFromLineup(person, lineup);
    }

}
