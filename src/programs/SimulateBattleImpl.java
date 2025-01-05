package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;

import java.util.*;

public class SimulateBattleImpl implements SimulateBattle {
    private PrintBattleLog printBattleLog; // Позволяет логировать. Использовать после каждой атаки юнита
    private final Comparator<Unit> comparator = (a, b) -> {
        var baseAttack1 = a.getBaseAttack();
        var baseAttack2 = b.getBaseAttack();
        if (baseAttack1 == baseAttack2) return 0;
        return baseAttack2 - baseAttack1;
    };
    int count = 0;

    @Override
    public void simulate(Army playerArmy, Army computerArmy) throws InterruptedException {
        List<Unit> playerUnitsList = new ArrayList<>(playerArmy.getUnits());
        List<Unit> compUnitsList = new ArrayList<>(computerArmy.getUnits());
        Iterator<Unit> playerIter = sortAndFilter(playerUnitsList).iterator();
        Iterator<Unit> computerIter = sortAndFilter(compUnitsList).iterator();
        int round = 0;
        count = 0;

        while (!playerUnitsList.isEmpty() && !compUnitsList.isEmpty()) {
            int step = 0;
            System.out.println("\nNew round " + ++round + " has started");

            while (playerIter.hasNext() || computerIter.hasNext()) {
                System.out.println("Round " + round + " step " + ++step);

                simulation("\tPlayer", playerIter);
                simulation("\tComputer", computerIter);
            }
            playerIter = sortAndFilter(playerUnitsList).iterator();
            computerIter = sortAndFilter(compUnitsList).iterator();
            if (playerUnitsList.isEmpty() || compUnitsList.isEmpty()) {
                int totalUnits = computerArmy.getUnits().size() + playerArmy.getUnits().size();

                printComplexity(totalUnits);
            }
        }
    }


    private List<Unit> sortAndFilter(List<Unit> armyUnits) {
        List<Unit> sortedAndFiltered = armyUnits.stream().filter(Unit::isAlive).sorted(comparator).toList();
        armyUnits.clear();
        armyUnits.addAll(sortedAndFiltered);
        return armyUnits;
    }

    private void simulation(String armySide, Iterator<Unit> armyIter) throws InterruptedException {
        if (armyIter.hasNext()) {
            Unit soldier = armyIter.next();
            ++count;

            System.out.print(armySide + " " + soldier.getName());
            if (soldier.isAlive()) {
                Unit enemy = soldier.getProgram().attack();
                if (enemy != null && enemy.getxCoordinate() == soldier.getxCoordinate()
                        && enemy.getyCoordinate() == soldier.getyCoordinate()) {
                    enemy = null;
                }
                if (enemy != null && enemy.getHealth() + soldier.getBaseAttack() > 0) {
                    printBattleLog.printBattleLog(soldier, enemy);
                    System.out.println(" - attacked -" + soldier.getBaseAttack()
                            + " points from enemy " + enemy.getName() + ","
                            + " health remained " + enemy.getHealth());
                } else if (enemy != null && enemy.getHealth() + soldier.getBaseAttack() <= 0) {
                    System.out.println(" - enemy " + enemy.getName() + " was dead");
                } else {
                    System.out.println(" - path to enemy not found");
                }
            } else {
                armyIter.remove();
                System.out.println(" - was removed");
            }
        }
    }

    private void printComplexity(int totalUnits) {
        System.out.println("\n-------------------------------------------------------" +
                "\nMethod SimulatedBattleImpl" +
                "\n\tExpected complexity n^2 * log n = " + Math.pow(totalUnits, 2) * Math.log(totalUnits) / Math.log(2)
                + "\n\tActual complexity = " + count + "\n-------------------------------------------------------");

        System.out.println("\n-------------------------------------------------------" +
                "\nMethod SuitableForAttackUnitsFinderImpl" +
                "\n\tExpected complexity n * m " + "\n\tActual complexity n * m, " +
                "где m - кол-во рядов (всегда 3), n - кол-во солдат каждой из сторон" +
                "\n-------------------------------------------------------");

        System.out.println("\n-------------------------------------------------------" +
                "\nMethod UnitTargetPathFinderImpl" +
                "\n\tExpected complexity Width * Height log (Width * Height) = "
                + (27 * 21) * Math.log(27 * 21) / Math.log(2)
                + "\n\tActual complexity Width * Height = " + 27 * 21
                + "\n-------------------------------------------------------");
    }
}