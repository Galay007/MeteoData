package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.util.*;

public class GeneratePresetImpl implements GeneratePreset {

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {
        Comparator<Unit> comparator = (a, b) -> {
            var ratioCost1 = (double) a.getHealth() / a.getCost() + (double) a.getBaseAttack() / a.getCost();
            var ratioCost2 = (double) b.getHealth() / b.getCost() + (double) b.getBaseAttack() / b.getCost();

            if (ratioCost1 - ratioCost2 > 0.00001) return -1;
            if (ratioCost1 - ratioCost2 < -0.00001) return 1;
            return 0;
        };

        unitList.sort(comparator);

        Army compArmy = new Army();
        int compPoints = 0;
        int x = 2;
        int y = 0;
        boolean isFull = false;
        int count = 0;

        for (Unit unitType : unitList) {
            if (!isFull) {
                for (int i = 0; i < 11 && compPoints + unitType.getCost() <= maxPoints; i++) {
                    ++count;
                    if (x == -1) {
                        isFull = true;
                        break;
                    }

                    compArmy.getUnits().add(new Unit(unitType.getName() + " " + (i + 1)
                            , unitType.getUnitType()
                            , unitType.getHealth()
                            , unitType.getBaseAttack()
                            , unitType.getCost()
                            , unitType.getAttackType()
                            , unitType.getAttackBonuses()
                            , unitType.getDefenceBonuses()
                            , x
                            , y++));

                    compPoints += unitType.getCost();

                    if (y == 21) {
                        x--;
                        y = 0;
                    }
                }
            }
        }

        System.out.println("-------------------------------------------------------\nMethod GeneratePresetImpl" +
                "\n\tExpected complexity n * m = " + (4 * 11)
                + "\n\tActual complexity = " + count + "\n-------------------------------------------------------");
        return compArmy;
    }
}