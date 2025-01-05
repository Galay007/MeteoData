package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.Edge;
import com.battle.heroes.army.programs.EdgeDistance;
import com.battle.heroes.army.programs.UnitTargetPathFinder;

import java.util.*;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    List<Edge> edges = new ArrayList<>();
    PriorityQueue<EdgeDistance> queue;
    boolean isFound;
    int[][] dist;
    Edge[][] parent;
    Unit[][] currentField;

    @Override
    public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {
        Edge target = new Edge(targetUnit.getxCoordinate(), targetUnit.getyCoordinate());
        Edge start = new Edge(attackUnit.getxCoordinate(), attackUnit.getyCoordinate());


        fillCurrentField(existingUnitList);
        findPath(start, target);
        updateEdges(target, start);

        return edges;
    }

    private void fillCurrentField(List<Unit> existingUnitList) {
        currentField = new Unit[27][21];
        for (Unit unit : existingUnitList) {
            if (unit.isAlive()) {
                currentField[unit.getxCoordinate()][unit.getyCoordinate()] = unit;
            }
        }
    }

    private void findPath(Edge start, Edge target) {
        edges.clear();
        dist = new int[27][21];
        parent = new Edge[27][21];
        isFound = false;
        dist[start.getX()][start.getY()] = 0;
        boolean[][] visited = new boolean[27][21];

        queue = new PriorityQueue<>(Comparator.comparingInt(EdgeDistance::getDistance));
        queue.add(new EdgeDistance(start.getX(), start.getY(), 0));
        visited[start.getX()][start.getY()] = true;

        while (!queue.isEmpty()) {
            EdgeDistance current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.getX() + dx[i];
                int newY = current.getY() + dy[i];

                if (isValid(newX, newY) && !visited[newX][newY] && !isFound && currentField[newX][newY] == null) {
                    visited[newX][newY] = true;
                    dist[newX][newY] = current.getDistance() + 1;
                    parent[newX][newY] = new Edge(current.getX(), current.getY());
                    queue.add(new EdgeDistance(newX, newY, current.getDistance() + 1));
                }
                if (newX == target.getX() && newY == target.getY()) {
                    dist[newX][newY] = current.getDistance() + 1;
                    parent[newX][newY] = new Edge(current.getX(), current.getY());
                    isFound = true;
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < 27 && y >= 0 && y < 21;
    }

    private void updateEdges(Edge target, Edge start) {
        if (parent[target.getX()][target.getY()] != null) {
            edges.add(target);

            while (target.getX() != start.getX() || target.getY() != start.getY()) {
                target = parent[target.getX()][target.getY()];
                edges.add(target);
            }
            Collections.reverse(edges);
        }
    }
}
