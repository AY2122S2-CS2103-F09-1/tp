package seedu.address.model.lineup;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;



/**
 * Represents a list of unique Teams
 */
public class UniqueLineupList implements Iterable<Lineup> {
    private final ObservableList<Lineup> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lineup> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a lineup to the list
     *
     * @param lineup The lineup to be added
     */
    public void addLineupToList(Lineup lineup) {
        requireNonNull(lineup);
        this.internalList.add(lineup);
    }

    /**
     * Deletes a lineup from the list
     *
     * @param lineup The lineup to be deleted
     */
    public void deleteLineupFromList(Lineup lineup) {
        requireNonNull(lineup);
        this.internalList.remove(lineup);
    }

    /**
     * Deletes a lineup from the list using lineup name
     *
     * @param lineupName The name of the lineup to be deleted
     */
    public void deleteLineupNameFromList(LineupName lineupName) {
        requireNonNull(lineupName);
        for (Lineup lineup : internalList) {
            if (lineup.sameLineupName(lineupName)) {
                this.internalList.remove(lineup);
            }
        }
    }

    /**
     * Checks for the list contains a lineup
     *
     * @param lineup The lineup to be checked
     * @return Boolean represents the existence of the lineup
     */
    public boolean containsLineup(Lineup lineup) {
        requireNonNull(lineup);
        return this.internalList.contains(lineup);
    }

    /**
     * Checks for any lineup in the UniqueLineupList has the same lineupName
     *
     * @param lineupName The lineupName to check
     * @return Boolean represents the existence of the lineup name
     */
    public boolean containsLineupName(LineupName lineupName) {
        requireNonNull(lineupName);
        for (Lineup lineup : internalList) {
            if (lineup.sameLineupName(lineupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter of lineup using LineupName
     *
     * @param lineupName The name of the target lineup
     * @return The target lineup which has the same LineupName
     */
    public Lineup getLineup(LineupName lineupName) {
        if (!containsLineupName(lineupName)) {
            return null;
        } else {
            for (Lineup lineup: internalList) {
                if (lineup.sameLineupName(lineupName)) {
                    return lineup;
                }
            }
        }

        return null;
    }

    /**
     * Replaces a old lineup by a new lineup
     * @param target The old lineup to be replaced
     * @param editedLineup The new lineup to replace the old lineup
     */
    public void replaceLineup(Lineup target, Lineup editedLineup) {
        this.deleteLineupFromList(target);
        this.addLineupToList(editedLineup);
    }

    /**
     * Puts a player into a Lineup in the UniqueLineupList
     *
     * @param player The player to put
     * @param lineup The lineup to put at
     */
    public void putPlayerToLineup(Person player, Lineup lineup) {
        requireNonNull(lineup);
        requireNonNull(player);
        if (containsLineup(lineup)) {
            lineup.addPlayer(player);
        }
    }

    /**
     * Deletes a player from a Lineup in the UniqueLineupList
     *
     * @param player The player to delete
     * @param lineup The Lineup to delete from
     */
    public void deletePlayerFromLineup(Person player, Lineup lineup) {
        requireNonNull(lineup);
        requireNonNull(player);
        if (containsLineup(lineup)) {
            lineup.removePlayer(player);
        }
    }

    /**
     * Replaces an old player in a lineup by a new player
     *
     * @param removedPlayer The player to be removed from the lineup
     * @param addedPlayer The player to be added into the lineup
     * @param lineup The target lineup
     */
    public void replacePlayerInLineup(Person removedPlayer, Person addedPlayer, Lineup lineup) {
        if (containsLineup(lineup)) {
            deletePlayerFromLineup(removedPlayer, lineup);
            putPlayerToLineup(addedPlayer, lineup);
        }
    }

    /**
     * Deletes a player from all lineups
     *
     * @param removedPlayer The player to be deleted
     */
    public void deletePlayerFromALlLineups(Person removedPlayer) {
        for (Lineup lineup : internalList) {
            deletePlayerFromLineup(removedPlayer, lineup);
        }
    }

    /**
     * Replaces a player from all lineups
     *
     * @param removedPlayer The player to be replaced
     * @param addedPlayer The new player to replace the old player
     */
    public void replacePlayerInAllLineups(Person removedPlayer, Person addedPlayer) {
        for (Lineup lineup : internalList) {
            replacePlayerInLineup(removedPlayer, addedPlayer, lineup);
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lineup> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lineup> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLineupList // instanceof handles nulls
                && internalList.equals(((UniqueLineupList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}

