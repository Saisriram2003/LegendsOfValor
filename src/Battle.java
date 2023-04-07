// Class that handles Battle Functionality

import java.util.ArrayList;
import java.util.Random;
import java.lang.Character;

public class Battle {
    private Hero activeHero;
    private Monster activeMonster;
    private boolean inBattle;
    private boolean turnEnd = false;
    Random random = new Random();

    public Battle(Hero hero,Monster monster) {
        this.activeHero = hero;
        this.activeMonster = monster;
        inBattle = true;
    }

    public Hero getActiveHero() {
        return activeHero;
    }
    public void setActiveHero(Hero hero){
        this.activeHero = hero;
    }

    // Start Battle
    public boolean startBattle(){
        while(inBattle){
            showBattleInfo();
            if(!activeHero.isAlive()){
                System.out.println("You lost the battle! You will be respawned at your Nexus.");
                activeHero.respawn(Legends.board);
                return false;
            }
            while(!turnEnd){
                turnEnd = displayBattleOptions();
            };
            turnEnd = false;
            if(activeMonster.isAlive()){
                activeMonster.monsterAttack(activeHero);
            }
            else{
                System.out.println("\u001B[32m" + "You Win!" + "\u001B[0m");
                heroWinBonus();
                // reset Monster Health for game purposes so next time Monster spawns it has full Health
                activeMonster.setHP(activeMonster.getMaxHP());
                Cell monsterCell = Legends.board.getCell(activeMonster.getCurrRow(),activeMonster.getCurrCol());
                Legends.monsterTeam.remove(monsterCell.getMonster());
                monsterCell.removeMonster();
                return true;
            }
            // All Heroes that are alive need to regain 5% Health and 5% Mana
            activeHero.setHP(activeHero.getHP() * 1.05);
            activeHero.setMP(activeHero.getMP() * 1.05);
        }
        return false;
    }

    // Bonus for winning a battle
    public void heroWinBonus(){
            Hero hero = activeHero;
            hero.setExp(hero.getExp() + activeMonster.expRecieved()); // Add exp based on monster team size * 2
            // Check if hero leveled up
            while(hero.getExp() >= hero.getExpToLevel()){
                hero.levelUp();
                System.out.println("\u001B[32m" + hero.getName() + " leveled up!" + "\u001B[0m");
            }
            hero.setGold(hero.getGold() + activeMonster.goldDropped()); // Add gold based on monster team size * 100
            hero.setHP(hero.getMaxHP());
            hero.setMP(hero.getOriginalMP());
            System.out.println("\u001B[32m" + "Hero Stats" + "\u001B[0m");
            hero.showStats();
    }

    // Show Battle Info
    public void showBattleInfo(){
        System.out.println("\u001B[32m" + "Active Hero" + "\u001B[0m");
        activeHero.showStats();
        System.out.println("\u001B[31m" + "Active Monster" + "\u001B[0m");
        activeMonster.showStats();
        System.out.println();
    }

    // Display Battle Options
    public boolean displayBattleOptions() {
        System.out.println("A: Attack");
        System.out.println("S: Cast a spell");
        System.out.println("P: Use a potion");
        System.out.println("E: Equip a weapon or piece of armor");
        System.out.println("Q: Quit game");
        char input = InputValidation.getValidChar("Select an option from above: ");
        return processBattleInput(input);
}

// Once a User chooses an option, process the input
public boolean processBattleInput(char input) {
    Inventory activeInventory = activeHero.getInventory();
    switch(Character.toLowerCase(input)) {
        case 'a':
            activeHero.heroAttack(activeMonster);
            return true;
        case 's':
            // Cast a spell
            if(activeInventory.enterSpellMenu(activeHero, activeMonster)){
                return true;
            }
            else{
                return false;
            }
        case 'p':
            // Use a potion
            if(activeInventory.enterPotionMenu(activeHero)){
                return true;
            }
            else{
                return false;
            }
        case 'e':
            // Equip a weapon or piece of armor
            if(activeInventory.enterEquipItemMenu(activeHero)){
                return true;
            }
            return false;
        case 'q':
            endGame();
            return true;
        default:
            System.out.println("Invalid input. Please enter a character in the options given!");
            return false;
    }
}

public void endGame(){
    System.out.println("You have fled from your Battle");
}
}

