package org.example;

import lombok.*;

import java.util.Random;

public class TextGame {
    public static void main(String[] args) {
        Character player1 = new CharacterFactory().createCharacter();
        Character player2 = new CharacterFactory().createCharacter();
        GameManager manager = new GameManager();
        manager.fight(player1, player2);
    }
}
@AllArgsConstructor
class Character {
    @Getter
    private int hp;
    @Getter @Setter
    private int power;

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }

    void kick(Character c){
        c.toDecreaseHP(this.getPower());
        if (c.getHp() < 0){
            c.setHp(0);
        }
    }

    public boolean isAlive(){
        return hp > 0;
    }

    void toDecreaseHP(int amount){
        hp -= amount;
    }

    @Override public String toString(){
        return getClass().getSimpleName() + "{hp=" + getHp() + ", power=" + getPower() + "}";
    }
}
class Hobbit extends Character{

    public Hobbit() {
        super(3, 0);
    }

    void kick(Character c){
        System.out.println("Why am I so weak???(((");
    }
}
class Elf extends Character{
    public Elf() {
        super(10, 10);
    }
    void kick(Character c){
        super.kick(c);
        if (this.getPower() <= c.getHp()){
            this.setPower(getPower() - 1);
        }
    }
}
class King extends Character{
    public King(){
        super(new Random().nextInt(11) + 5, new Random().nextInt(11) + 5);
    }
}
class Knight extends Character{
    public Knight(){
        super(new Random().nextInt(11) + 2, new Random().nextInt(11) + 2);
    }
}

class CharacterFactory{
    public Character createCharacter(){
        Random rn = new Random();
        int rnNum = rn.nextInt(4) + 1;
        if (rnNum == 1){
            return new Hobbit();
        } else if (rnNum == 2) {
            return new Elf();
        } else if (rnNum == 3) {
            return new King();
        } else {
            return new Knight();
        }
    }
}

class GameManager{
    void fight(Character player1, Character player2){
        String name1 = player1.getClass().getSimpleName();
        String name2 = player2.getClass().getSimpleName();
        System.out.println("Hi there!");
        System.out.println("There are two fighters: " + name1 + " and " + name2 + ".");
        System.out.println(player1.toString());
        System.out.println(player2.toString());
        System.out.println("The fight are starting! Good luck for every fighter!");
        int checker = 0;
        boolean alive = true;
        String winner = "";
        if (name1.equals("Hobbit") && name2.equals("Hobbit")){
            alive = false;
            winner = "'Ha-ha, there are no winner)'";
        }
        while (alive){
            if (checker == 0){
                System.out.println(name1 + " kicks " + name2);
                player1.kick(player2);
                checker = 1;
                if (!player2.isAlive()){
                    player2.setHp(0);
                    alive = false;
                    winner = name1;
                }
            } else {
                System.out.println(name2 + " kicks " + name1);
                player2.kick(player1);
                checker = 0;
                if (!player1.isAlive()){
                    player1.setHp(0);
                    alive = false;
                    winner = name2;
                }
            }
            System.out.println("Data after kicking: " + player1.toString() + ", " + player2.toString() + ".");
        }
        System.out.println("The winner is: " + winner + ". Congratulations!");
    }
}
