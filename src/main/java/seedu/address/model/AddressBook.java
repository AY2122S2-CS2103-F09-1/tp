package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.schedule.UniqueScheduleList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueLineupList lineups;
    private final UniqueScheduleList schedules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        lineups = new UniqueLineupList();
        schedules = new UniqueScheduleList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if {@code targetName} is taken by some player.
     */
    public boolean hasPersonName(Name targetName) {
        requireNonNull(targetName);
        return persons.containsName(targetName);
    }

    public boolean hasLineupName(LineupName targetName) {
        requireNonNull(targetName);
        return lineups.containsLineupName(targetName);
    }

    /**
     * Returns the person with {@code targetName};
     */
    public Person getPerson(Name targetName) {
        return persons.getPerson(targetName);
    }

    public Lineup getLineup(LineupName targetName) {
        return lineups.getLineup(targetName);
    }

    public void addPersonToLineup(Person person, Lineup lineup) {
        lineup.addPlayer(person);
    }

    /**
     * Returns true if the person to add has a duplicate jersey number.
     */
    public boolean hasJerseyNumber(Person player) {
        return persons.containsJerseyNumber(player.getJerseyNumber());
    }

    /**
     * Returns true if MyGM has reached maximum capacity.
     */
    public boolean isFull() {
        return persons.isFull();
    }

    /**
     * Returns a list of jersey number that are still available.
     * @return
     */
    public String getAvailableJerseyNumber() {
        return persons.getAvailableJerseyNumber();
    }


    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
        lineups.replacePlayerInAllLineups(editedPerson, target);
    }

    public void setLineup(Lineup target, Lineup editedLineup) {
        requireNonNull(editedLineup);
        lineups.replaceLineup(target, editedLineup);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        lineups.deletePlayerFromALlLineups(key);
    }

    /**
     * Adds a Lineup to MyGM
     *
     * @param lineup The Lineup to be added
     */
    public void addLineup(Lineup lineup) {
        lineups.addLineupToList(lineup);
    }

    /**
     * Removes a Lineup from MyGM
     *
     * @param lineup The Lineup to be removed
     */
    public void removeLineup(Lineup lineup) {
        lineups.deleteLineupFromList(lineup);
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
