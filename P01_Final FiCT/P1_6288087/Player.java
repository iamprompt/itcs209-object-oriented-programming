import java.util.Objects;

public class Player {

    public enum PlayerType {Healer, Tank, Samurai, BlackMage, Phoenix, Cherry}

    private PlayerType type;    // Type of this player. Can be one of either Healer, Tank, Samurai, BlackMage, or Phoenix
    private double maxHP;       // Max HP of this player
    private double currentHP;   // Current HP of this player
    private double atk;         // Attack power of this player

    /**
     * Additional
     **/
    private int numOfSpecial = 0;
    private int numTurns = 0;

    // State
    private boolean isSleeping = false;
    private boolean isCursed = false;
    private boolean isTaunting = false;

    private Player Cursing = null;
    private Player CursedBy = null;

    /**
     * Constructor of class Player, which initializes this player's type, maxHP, atk, numSpecialTurns,
     * as specified in the given table. It also reset the internal turn count of this player.
     *
     * @param _type
     */
    public Player(PlayerType _type) {
        this.type = _type; // Set Type
        switch (_type) {
            case Healer:
                setInitial(4790, 238, 4);
                break;
            case Tank:
                setInitial(5340, 255, 4);
                break;
            case Samurai:
                setInitial(4005, 368, 3);
                break;
            case BlackMage:
                setInitial(4175, 303, 4);
                break;
            case Phoenix:
                setInitial(4175, 209, 8);
                break;
            case Cherry:
                setInitial(3560, 198, 4);
                break;
        }
    }

    // Additional Method for setting the initial value
    private void setInitial(double maxHP, double atk, int numOfSpecial) {
        this.maxHP = maxHP;
        this.currentHP = this.maxHP; // Initial HP Value equals to maxHP
        this.atk = atk;
        this.numOfSpecial = numOfSpecial;
        this.numTurns = 0;
    }

    /**
     * Returns the current HP of this player
     *
     * @return
     */
    public double getCurrentHP() {
        return this.currentHP;
    }

    /**
     * Returns type of this player
     *
     * @return
     */
    public PlayerType getType() {
        return this.type;
    }

    /**
     * Returns max HP of this player.
     *
     * @return
     */
    public double getMaxHP() {
        return this.maxHP;
    }

    /**
     * Returns whether this player is sleeping.
     *
     * @return
     */
    public boolean isSleeping() {
        return this.isSleeping;
    }

    /**
     * Returns whether this player is being cursed.
     *
     * @return
     */
    public boolean isCursed() {
        return this.isCursed;
    }

    /**
     * Returns whether this player is alive (i.e. current HP > 0).
     *
     * @return
     */
    public boolean isAlive() {
        return this.currentHP > 0;
    }

    /**
     * Returns whether this player is taunting the other team.
     *
     * @return
     */
    public boolean isTaunting() {
        return this.isTaunting;
    }


    public void attack(Player target) {
        if (target != null) {
            target.currentHP = (target.currentHP - this.atk <= 0 ? 0 : target.currentHP - this.atk);
            this.numTurns++;
        }
    }

    private enum PowerName {Heal, Taunt, DoubleSlash, Curse, Revive, FortuneCookies, NormalAttack}

    public void useSpecialAbility(Arena arena) {
        switch (this.type) {
            case Healer:
                saHeal(arena);
                break;
            case Tank:
                saTaunt(arena);
                break;
            case Samurai:
                saDoubleSlash(arena);
                break;
            case BlackMage:
                saCurse(arena);
                break;
            case Phoenix:
                saRevive(arena);
                break;
            case Cherry:
                saKFC(arena);
                break;
        }
        this.numTurns = 0;
    }

    /**
     * Log
     * # A[Back][1] {BlackMage} Attacks B[Back][2] {Healer}
     * # A[Back][2] {Tank} Attacks B[Back][2] {Healer}
     * # A[Back][3] {BlackMage} Attacks B[Back][2] {Healer}
     * # A[Back][4] {Cherry} Attacks B[Back][2] {Healer}
     * # A[Back][5] {Samurai} Attacks B[Back][2] {Healer}
     * # B[Back][2] {Healer} Attacks A[Back][4] {Cherry}
     */

    private void logPower(Arena arena, PowerName power, Player target) {
        switch (power) {
            case Heal:
                System.out.println("# " + this.shortString(arena) + " Heals " + target.shortString(arena));
                break;
            case Taunt:
                System.out.println("# " + this.shortString(arena) + " is Taunting");
                break;
            case DoubleSlash:
                System.out.println("# " + this.shortString(arena) + " Double-Slashes " + target.shortString(arena));
                break;
            case Curse:
                System.out.println("# " + this.shortString(arena) + " Curses " + target.shortString(arena));
                break;
            case Revive:
                System.out.println("# " + this.shortString(arena) + " Revives " + target.shortString(arena));
                break;
            case FortuneCookies:
                System.out.println("# " + this.shortString(arena) + " Feeds a Fortune Cookie to " + target.shortString(arena));
                break;
            case NormalAttack:
                System.out.println("# " + this.shortString(arena) + " Attacks " + target.shortString(arena));
                break;
        }
    }


    /**
     * This method is called by Arena when it is this player's turn to take an action.
     * By default, the player simply just "attack(target)". However, once this player has
     * fought for "numSpecialTurns" rounds, this player must perform "useSpecialAbility(myTeam, theirTeam)"
     * where each player type performs his own special move.
     *
     * @param arena
     */
    public void takeAction(Arena arena) {
        //INSERT YOUR CODE HERE
        this.resetCurse();
        this.resetTaunt();
        if (this.isAlive() && !(this.isSleeping())) {
            if (this.numTurns != this.numOfSpecial - 1) {
                Player target = getTarget(arena);
                if (target != null) {
                    attack(target);
                    logPower(arena, PowerName.NormalAttack, target);
                }
            } else {
                useSpecialAbility(arena);
            }
        }
        this.resetSleeping();
    }

    private Player getTarget(Arena arena) {
        for (Player[] o : arena.getOtherTeam(this)) {
            for (Player oP : o) {
                if (oP.isTaunting() && oP.isAlive()) return oP;
            }
        }
        return Arena.getLowestHP(arena.getOtherTeam(this));
    }

    /**
     * This method overrides the default Object's toString() and is already implemented for you.
     */
    @Override
    public String toString() {
        return "[" + this.type.toString() + " HP:" + this.currentHP + "/" + this.maxHP + " ATK:" + this.atk + "]["
                + ((this.isCursed()) ? "C" : "")
                + ((this.isTaunting()) ? "T" : "")
                + ((this.isSleeping()) ? "S" : "")
                + "]";
    }

    // B[Back][2]
    private String shortString(Arena arena) {
        Arena.Team team = arena.isMemberOf(this, Arena.Team.A) ? Arena.Team.A : Arena.Team.B;
        Player[][] myTeam = arena.getMyTeam(this);
        int i = 0, j = 0;
        for (i = 0; i < myTeam.length; i++) {
            for (j = 0; j < myTeam[i].length; j++) {
                if (this == myTeam[i][j]) {
                    Arena.Row row = i == 0 ? Arena.Row.Front : Arena.Row.Back;
                    int position = j + 1;
                    return team + "[" + row + "][" + position + "] {" + this.type + "}";
                }
            }
        }
        return null;
    }

    private void resetAllstate() {
        this.isSleeping = false;
        this.isCursed = false;
        this.isTaunting = false;
        this.numTurns = 0;
    }

    // Special Ability
    private void saHeal(Arena arena) {
        Player[][] myTeam = arena.getMyTeam(this);
        Player p = Arena.getLowestHP(myTeam);
        if (p != null) {
            p.currentHP += (p.getMaxHP() * 0.25);
            p.currentHP = p.currentHP >= p.maxHP ? p.maxHP : p.currentHP;
            logPower(arena, PowerName.Heal, p);
        }
    }

    // TODO: reset the state (isTaunting)
    private void saTaunt(Arena arena) {
        this.isTaunting = true;
        logPower(arena, PowerName.Taunt, null);
    }

    private void resetTaunt() {
        this.isTaunting = false;
    }

    private void saDoubleSlash(Arena arena) {
        Player[][] theirTeam = arena.getOtherTeam(this);
        Player target = Arena.getLowestHP(theirTeam);
        if (target != null) {
            attack(target);
            attack(target);

            logPower(arena, PowerName.DoubleSlash, target);
        }
    }

    // TODO: reset the state (isCursed)
    private void saCurse(Arena arena) {
        Player[][] theirTeam = arena.getOtherTeam(this);
        Player toCurse = Arena.getLowestHP(theirTeam);
        if (toCurse != null) {
            toCurse.isCursed = true;
            toCurse.CursedBy = this;
            this.Cursing = toCurse;
            logPower(arena, PowerName.Curse, toCurse);
        }
    }

    private void resetCurse() {
        if (this.Cursing != null) {
            if (this.Cursing.CursedBy == this) // Prevent overriding curse state
                this.Cursing.isCursed = false;
            this.Cursing = null;
        }
    }

    private void saRevive(Arena arena) {
        Player[][] myTeam = arena.getMyTeam(this);
        Player toRevive = Arena.getDead(myTeam);
        if (toRevive != null) {
            toRevive.currentHP = toRevive.getMaxHP() * 0.3;
            toRevive.resetAllstate();
            logPower(arena, PowerName.Revive, toRevive);
        }
    }

    // TODO: Reset all players' isSleeping state when finish each round
    private void saKFC(Arena arena) {
        Player[][] theirTeam = arena.getOtherTeam(this);
        for (Player[] o : theirTeam) {
            for (Player op : o) {
                if (op.isAlive()) {
                    op.isSleeping = true;
                    logPower(arena, PowerName.FortuneCookies, op);
                }
            }
        }
    }

    // TODO: Edit Logic
    private void resetSleeping() {
        if (this.isSleeping()) this.isSleeping = false;
    }

}
