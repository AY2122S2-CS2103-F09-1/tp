package seedu.address.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

import javax.sound.sampled.Line;

/**
 * Represents all plauers contained in the system..
 * Contains the set of all players, a map from players to their teams,
 * and a map from players to their lineups.
 */
public class UniquePlayerList {
    private final Map<Name, Person> nameToPersonMap;
    private final Map<Person, Team> personToTeamMap;
    private final Map<Person, List<Lineup>> personToLineupMap;

    /**
     * Cretes a new MyGM object.
     */
    public UniquePlayerList() {
        this.nameToPersonMap = new HashMap<Name, Person>();
        this.personToTeamMap = new HashMap<Person, Team>();
        this.personToLineupMap = new HashMap<Person, List<Lineup>>();
    }

    /**
     * Gets the list of lineups a player belongs to.
     */
    public List<Lineup> getPlayerLineup(Person player) {
        if (this.containsPerson(player)) {
            return this.personToLineupMap.get(player);
        }
        return new ArrayList<Lineup>();
    }

    /**
     * Checks if the given name is in the player pool.
     *
     * @param name
     * @return
     */
    public boolean containsName(Name name) {
        return this.nameToPersonMap.containsKey(name);
    }

    /**
     * Checks if the given person exists.
     */
    public boolean containsPerson(Person person) {
        return containsName(person.getName());
    }

    /**
     * Adds a person to the system.
     */
    public void addPerson(Person person) {
        Name name = person.getName();
        if (!this.nameToPersonMap.containsKey(name)) {
            this.nameToPersonMap.put(name, person);
        }
    }

    /**
     * Adds a person to team mapping to the system.
     */
    public void addPersonToTeam(Person person, Team team) {
        this.personToTeamMap.put(person, team);
    }

    /**
     * Adds a person to lineup mapping to the system.
     */
    public void addPersonToLineup(Person person, Lineup lineup) {
        if (this.personToLineupMap.containsKey(person)) {
            this.personToLineupMap.get(person).add(lineup);
        } else {
            this.personToLineupMap.put(person, new ArrayList<Lineup>());
            this.personToLineupMap.get(person).add(lineup);
        }
    }

    /**
     * Removes a person from the system.
     */
    public void removePerson(Person person) {
        if (!this.nameToPersonMap.containsKey(person.getName())) {
            this.nameToPersonMap.remove(person);
        }
    }

    /**
     * Remoes a person to team mapping from the system.
     */
    public void removePersonFromTeam(Person person, Team team) {
        if (this.personToTeamMap.get(person) == team) {
            // not .equal() here because they are supposed to be the same team
            this.personToTeamMap.remove(person, team);
        }
    }

    /**
     * Removes a person to lineup mapping from the system.
     */
    public void removePersonFromLineup(Person person, Lineup lineup) {
        if (this.personToLineupMap.get(person).contains(lineup)) {
            this.personToLineupMap.get(person).remove(lineup);
        }
    }
}
