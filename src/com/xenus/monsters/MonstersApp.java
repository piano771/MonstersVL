package com.xenus.monsters;

import java.util.Scanner;

public class MonstersApp {
    private static Scanner sc = new Scanner(System.in);

//    1. Грубо говоря - это просто заготовки действий персонажей и самих персонажей;
    static class GameCharacter {
        String name;
        int hp;
        int maxHp;
        int attackPower;
        boolean block;

//        Конструктор персонажей;
        public GameCharacter(String name, int maxHp, int attackPower) {
            this.name = name;
            this.maxHp = maxHp;
            this.hp = this.maxHp;
            this.attackPower = attackPower;
            this.block = false;
        }

        /*
        Конструктор действия "/атака";
        Условие можно выразить как: target.block == true;
         */
        public void attack(GameCharacter target) {
            int damage = this.attackPower;
            if(target.block) {
                if(Math.random() < 0.8) {
                    System.out.println(target.name + " полностью заблокировал атаку");
                    return;
                } else {
                    damage *= 2;
                }
            }
            target.hp -= damage;
            System.out.println("\n" + this.name + " нанёс персонажу " + target.name + " урон = " + damage);
            System.out.println("У персонажа " + target.name + " осталось " + target.hp + " / " + target.maxHp + " ед. здоровья");
        }

        /*
        Конструктор действия "/блок";
        hp += 1; / hp ++; - разные формы записи (второй случай: инкремент);
         */
        public void blockAction() {
            hp += 1;
            if (hp > maxHp) {
                hp = maxHp;
            }
            block = true;
            System.out.println(this.name + " пытается заблокировать следующую атаку. Получает +1 ед. здоровья");
        }

        //        Конструкция сбрасывающая блок каждый ход;
        public void reset() {
            block = false;
        }
    }

    //    2. Сам исполнительный код игры;
    public static void main(String[] args) {
        System.out.println("Приключение рыцаря\nВведите любой символ, что бы продолжить...");
        String anyKey = sc.next();

        System.out.println("\nОднажды, в одном далёком королевстве, случилась беда.\n" +
                           "На жителей этого королевства напал страшный монстр.\n" +
                           "Король, недолго думая, решил попросить помощи у его лучшего рыцаря.\n" +
                           "Этого рыцаря звали...\n'Введите имя рыцаря'");
        String name = sc.next();
        String heroName;

        System.out.println("\n'Выберите титул'\n1. Сэр-рыцарь;\n2. Лорд-рыцарь;\n3. @*$&#");
        String title = sc.next();
        if(title.equals("1")) {
            heroName = "Сэр " + name;
        } else if(title.equals("2")) {
            heroName = "Лорд " + name;
        } else if(title.equals("3")) {
            heroName = name + " Подзаборный";
        } else {
            heroName = name;
        }

        GameCharacter hero = new GameCharacter(heroName, 10, 2);
        GameCharacter monster = new GameCharacter("Тролль", 8, 3);

        System.out.println("\nИ вот " + hero.name + " примчался в деревню, где уже во всю орудует страшный " + monster.name + ".");

        System.out.println("Спрыгнув с коня, " + hero.name + ", берёт в руки меч с щитом и вступает в схватку!\n" +
                           "'Для атаки монстра введите команду: /ударить'\n" +
                           "'Для того, что бы поставить блок введите команду: /блок'\n" +
                           "'Для того, что бы пропустить ход, введите любую другую команду'");

        while (true) {
            System.out.println("\nХод героя: " + hero.name);
            System.out.println(hero.name + ", каковы будут ваши действия?");
            hero.reset();
            String input = sc.next();

            if (input.equals("/ударить")) {
                hero.attack(monster);
                if (monster.hp <= 0) {
                    System.out.println(hero.name + " победил персонажа " + monster.name + "!");
                    break;
                }
            } else if (input.equals("/блок")) {
                hero.blockAction();
            } else if (input.equals("3") && title.equals("3")) {
                System.out.println("Вы дышите на монстра своим ужасающим перегаром.\n"
                                   + monster.name + " явно не был готов к такому!\n" +
                                   "Истязаемый агониями он падает замертво.\n" +
                                   "Вы не просто победитель - вы ПОБЕДЮН!");
                break;
            }

            System.out.println("\nХод монстра: " + monster.name);
            monster.reset();
            if (Math.random() < 0.55) {
                monster.attack(hero);
                if (hero.hp <= 0) {
                    System.out.println(monster.name + " одолел персонажа " + hero.name + "!");
                    break;
                }
            } else {
                monster.blockAction();
            }
        }
        System.out.println("Спасибо за игру! :)");
    }
}
