package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.ArrayList;
import java.util.List;

public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {
    List<Unit> selectedUnits = new ArrayList<>();
    Unit[][] armyField;
    int x = 0;

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        x = 0;
        selectedUnits.clear();
        armyField = new Unit[3][21];

        //Алгоритм запускается либо для левой, либо для правой стороны
        if (!isLeftArmyTarget) {
            rightArmyTarget(unitsByRow);
        } else {
            leftArmyTarget(unitsByRow);
        }

        System.out.print(" (total enemy units = " + x + ", alive opened units = " + selectedUnits.size() + ")");
        return selectedUnits;
    }

    private void leftArmyTarget(List<List<Unit>> unitsByRow) {
        for (int i = 2; i >= 0; i--) { //m - кол-во рядов всегда фиксированно и равно 3м
            for (Unit unit : unitsByRow.get(i)) { //n - кол-во солдат, проходимся один раз по каждому солдату
                ++x;
                int yCoordinate = unit.getyCoordinate();
                int xCoordinate = unit.getxCoordinate();

                if (xCoordinate == 2) {
                    selectedUnits.add(unit);
                    armyField[xCoordinate][yCoordinate] = unit;
                } else if (armyField[xCoordinate + 1][yCoordinate] == null) {
                    selectedUnits.add(unit);
                    armyField[xCoordinate][yCoordinate] = unit;
                }
            }
        }
    }

    private void rightArmyTarget(List<List<Unit>> unitsByRow) {
        for (List<Unit> unitsInRow : unitsByRow) { //m - кол-во рядов всегда фиксированно и равно 3м
            for (Unit unit : unitsInRow) { //n - кол-во солдат, проходимся один раз по каждому солдату
                ++x;
                if (unit != null && unit.isAlive()) {
                    int yCoordinate = unit.getyCoordinate();
                    int xCoordinate = unit.getxCoordinate() - 24;

                    if (xCoordinate == 0) {
                        selectedUnits.add(unit);
                        armyField[xCoordinate][yCoordinate] = unit;
                    } else if (armyField[xCoordinate - 1][yCoordinate] == null) {
                        selectedUnits.add(unit);
                        armyField[xCoordinate][yCoordinate] = unit;
                    }
                }
            }
        }
    }
}
