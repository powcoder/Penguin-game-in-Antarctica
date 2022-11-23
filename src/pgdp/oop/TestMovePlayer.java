https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package pgdp.oop;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static pgdp.oop.Antarktis.antarktis;

public class TestMovePlayer {
    private static final TestPlayer[] tests = new TestPlayer[] {
            () -> {
                System.out.println("Feld:");
                System.out.println("pp");
                System.out.println("pp.move(0, 1);");

                var player = add(new PlayerPenguin(0, 0));

                var returnVal = player.move(0, 1);

                assert antarktis[0][1] == player : "Spieler steht nicht auf [0][1], sondern auf " + realPos(player) + ".";
                assert player.x == 0 && player.y == 1
                        : "x- und y-Attribut des Pinguins sind falsch. [0][1] erwartet, ist jedoch " + pos(player) + ".";
                assert antarktis[0][0] == null : "[0][0] ist nicht leer.";
                assert !returnVal : "Rückgabewert ist nicht false.";
            },
            () -> {
                System.out.println("Feld:");
                System.out.println("pp");
                System.out.println("pp.move(31, 4);");

                var player = add(new PlayerPenguin(0, 0));

                var returnVal = player.move(31, 4);

                assert antarktis[31][4] == player : "Spieler steht nicht auf [31][4], sondern auf " + realPos(player) +
                        ". Teleportation soll möglich sein.";
                assert player.x == 31 && player.y == 4
                        : "x- und y-Attribut des Pinguins sind falsch. [31][4] erwartet, ist jedoch " + pos(player) +
                        ". Teleportation soll möglich sein.";
                assert antarktis[0][0] == null : "[0][0] ist nicht leer.";
                assert !returnVal : "Rückgabewert ist nicht false.";
            },
            () -> {
                System.out.println("Feld:");
                System.out.println("pp - p");
                System.out.println("pp.move(2, 0);");

                var player = add(new PlayerPenguin(0, 0));
                add(new Penguin(2, 0));

                var returnVal = player.move(2, 0);

                assert returnVal : "Rückgabewert ist nicht true.";
            },
            () -> {
                System.out.println("Feld:");
                System.out.println("pp - s w");
                System.out.println("pp.move(2, 0);");

                var player = add(new PlayerPenguin(0, 0));
                add(new LeopardSeal(2, 0));
                add(new Whale(3, 0));

                var returnVal = player.move(2, 0);

                assert returnVal : "Rückgabewert ist nicht true.";
            },
            () -> {
                System.out.println("Feld:");
                System.out.println("pp - w");
                System.out.println("pp.move(2, 0);");

                var player = add(new PlayerPenguin(0, 0));
                add(new Whale(2, 0));

                var returnVal = player.move(2, 0);

                assert returnVal : "Rückgabewert ist nicht true.";
            },
            () -> {
                System.out.println("Feld:");
                System.out.println("pp - f w");
                System.out.println("pp.move(2, 0);");

                var player = add(new PlayerPenguin(0, 0));
                var fish = add(new Fish(2, 0));
                add(new Whale(3, 0));

                var returnVal = player.move(2, 0);

                assert antarktis[2][0] == player : "Spieler steht nicht auf [2][0], sondern auf " + realPos(player) + ".";
                assert player.x == 2 && player.y == 0
                        : "x- und y-Attribut des Pinguins sind falsch. [2][0] erwartet, ist jedoch " + pos(player) + ".";
                assert antarktis[0][0] == null : "[0][0] ist nicht leer.";
                assert !fish.alive : "Der Fisch ist nicht tot.";
                assert !returnVal : "Rückgabewert ist nicht false.";
            },
            () -> {
                System.out.println("Feld:");
                System.out.println("pp");
                System.out.println("pp.move(314, 159);");

                var player = add(new PlayerPenguin(0, 0));

                try {
                    player.move(314, 159);
                } catch (Exception e) {
                    e.printStackTrace();
                    assert false : "Obige Exception trat beim Testen mit einer Position außerhalb des Spielfeldes auf.";
                }
            }
    };

    private static Animal add(Animal animal) {
        antarktis[animal.x][animal.y] = animal;
        return animal;
    }

    private static PlayerPenguin add(PlayerPenguin player) {
        antarktis[player.x][player.y] = player;
        return player;
    }

    private static String pos(Animal animal) {
        return "[" + animal.x + "][" + animal.y + "]";
    }

    private static String realPos(Animal animal) {
        for (int x = 0; x < antarktis.length; ++x) {
            for (int y = 0; y < antarktis.length; ++y) {
                if (antarktis[x][y] == animal) return "[" + x + "][" + y + "]";
            }
        }

        return "gar keinem Feld";
    }

    private static void timeout() {
        new TimeoutException("Das dauert zu lange! Test abgebrochen. 2 Sekunden reichen für den Test aus.").printStackTrace();
        System.exit(1);
    }

    public static void main(String[] args) {
        boolean ea = false;
        assert ea = true;
        if (!ea) throw new UnsupportedOperationException("Du hast Assertions nicht aktiviert, " +
                "obwohl diese für diesen Test erforderlich sind. Füge zu deinen JVM-Optionen die Option -ea hinzu. " +
                "Wie das geht, ist in der pgdp-tests-Discussion beschrieben.");

        System.out.println("Diese Tests testen public boolean move(int newX, int newY) anhand mehrerer Beispiele.");
        System.out.println("Schlägt ein Test fehl, bricht das Programm sofort ab " +
                "und alle weiteren Tests werden nicht ausgeführt.");
        System.out.println("Keine Garantie auf Vollständigkeit und Richtigkeit!");
        System.out.println("Erstellt von Max Schröder :)");
        System.out.println("Der Test startet nun und wird maximal 2 Sekunden laufen.");
        System.out.println();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(TestMovePlayer::timeout, 2, TimeUnit.SECONDS);

        try {
            for (int i = 0; i < tests.length; ++i) {
                System.out.println("*** Test " + (i + 1) + " ***");
                antarktis = Antarktis.generateMaze(41, 41);
                Animal.setAntarktis(antarktis);
                tests[i].test();
                System.out.println("OK");
                System.out.println();
            }
        } finally {
            executor.shutdownNow();
        }

        System.out.println("        /@@@@@@@\\\n" +
                "      (@@@@@ # @@@@@\\\n" +
                "     (` \\@@@@@@@@~~~~\n" +
                "    /`    \\@@@@@|\n" +
                "   /@@     ''''  \\\n" +
                "  /@@@@\\          |\n" +
                " /@@@@@@@\\        |\n" +
                "/@@@@@@@@@        |\n" +
                "|@@@@@@@@         |\n" +
                "|@@@@@@@          |\n" +
                "|@@@@@@@          |\n" +
                "|@@@'@@@@        |\n" +
                "|@@@ '@@@        ;\n" +
                "|@@@  @@;       ;\n" +
                "|@@@  ''       ;\n" +
                "(@@@@         ;\n" +
                " (@@@@        |\n" +
                "  (__@@_______)");
        System.out.println();
        System.out.println("### Glückwunsch: Alle Tests bestanden! ###");
        System.out.println("### ACHTUNG: Diese Tests garantieren nicht, dass Dein Code fehlerfrei ist! ###");
    }
}

interface TestPlayer {
    void test();
}