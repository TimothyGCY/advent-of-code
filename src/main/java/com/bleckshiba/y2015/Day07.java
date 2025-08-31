package com.bleckshiba.y2015;

import com.bleckshiba.FileUtil;
import com.bleckshiba.Solution;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.IntBinaryOperator;

public class Day07 extends Solution<Integer> {
  private final Map<String, Wire> wires = new HashMap<>();
  private final Map<String, Gate> gates = new HashMap<>();

  private final List<String> data;
  private final String target;

  public Day07(List<String> data, String target) {
    this.data = data;
    this.target = target;
  }

  public static void main(String[] args) throws IOException {
    Day07 day07 = new Day07(FileUtil.readFile(2015, 7), "a");
    System.out.println(day07.solvePart1()); // 16076
    System.out.println(day07.solvePart2()); // 2797
  }

  @Override
  public Integer solvePart1() {
    createCircuit(data);
    return sendSignal(this.target);
  }

  @Override
  public Integer solvePart2() {
    wires.clear();
    gates.clear();
    int value = solvePart1();
    wires.clear();
    gates.clear();
    createCircuit(data);
    Gate b = getGate("b");
    b.getResult().setValue(value);
    b.setOp(null);
    return sendSignal(this.target);
  }

  private boolean isDigit(String input) {
    return Character.isDigit(input.charAt(0));
  }

  private Wire getWire(String input) {
    return isDigit(input)
      ? new Wire(input, Integer.parseInt(input))
      : wires.getOrDefault(input, new Wire(input));
  }

  private Gate getGate(String input) {
    return isDigit(input) ? new Gate(input) : gates.getOrDefault(input, new Gate(input));
  }

  private void createCircuit(List<String> input) throws UnknownError {
    for (String i : input) {
      String[] ins = i.split(" ");
      Wire left;
      Wire right = null;
      Wire result;
      Operator op;

      if (ins.length == 3) {
        left = getWire(ins[0]);
        result = getWire(ins[2]);
        op = Operator.ASSIGN;
      } else if (ins.length == 4) {
        left = getWire(ins[1]);
        result = getWire(ins[3]);
        op = Operator.NOT;
      } else if (ins.length == 5) {
        left = getWire(ins[0]);
        right = getWire(ins[2]);
        op = Operator.valueOf(ins[1]);
        result = getWire(ins[4]);
      } else {
        throw new UnknownError("Unknown case");
      }

      Gate gate = getGate(result.getName());
      gate.setLeft(left);
      gate.setRight(right);
      gate.setResult(result);
      gate.setOp(op);
      gates.put(gate.getResult().getName(), gate);
      storeWires(left, right, result);
    }
  }

  private void storeWires(Wire... wires) {
    for (Wire w : wires)
      if (w != null)
        this.wires.put(w.getName(), w);
  }

  private int sendSignal(String name) {
    final Gate gate = getGate(name);
    while (gate.getResult().getValue() == -1) {
      gate.sendSignal();
    }
    return gate.getResult().getValue();
  }

  private enum Operator {
    AND((a, b) -> a & b),
    OR((a, b) -> a | b),
    NOT((a, b) -> (~a & 0xffff)),
    RSHIFT((a, b) -> (a >> b)),
    LSHIFT((a, b) -> a << b),
    ASSIGN((a, b) -> a),
    ;

    private final IntBinaryOperator compute;

    Operator(IntBinaryOperator op) {
      this.compute = op;
    }
  }


  private static class Wire {
    private final String name;
    private Integer value;

    public Wire(String name) {
      this(name, -1);
    }

    public Wire(String name, Integer value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public Integer getValue() {
      return value;
    }

    public void setValue(Integer value) {
      this.value = value;
    }

  }


  private class Gate {
    private final String name;
    private Wire left;
    private Wire right;
    private Wire result;
    private Operator op;

    public Gate(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public Wire getLeft() {
      return left;
    }

    public void setLeft(Wire left) {
      this.left = left;
    }

    public Wire getRight() {
      return right;
    }

    public void setRight(Wire right) {
      this.right = right;
    }

    public Wire getResult() {
      return result;
    }

    public void setResult(Wire result) {
      this.result = result;
    }

    public Operator getOp() {
      return op;
    }

    public void setOp(Operator op) {
      this.op = op;
    }

    public void sendSignal() {
      if (allWiresHaveValue()) {
        perform();
      } else {
        if (left != null && !left.getName().isEmpty()) {
          Gate g = getGate(left.getName());
          g.sendSignal();
        }
        if (right != null && !right.getName().isEmpty()) {
          Gate g = getGate(right.getName());
          g.sendSignal();
        }
      }
    }

    private void perform() {
      if (op == null)
        return;
      result.setValue(
        op.compute.applyAsInt(Optional.ofNullable(left).map(Wire::getValue).orElse(0),
          Optional.ofNullable(right).map(Wire::getValue).orElse(0)));
    }

    private boolean allWiresHaveValue() {
      if (left != null && left.getValue() == -1)
        return false;
      return right == null || right.getValue() != -1;
    }
  }
}
