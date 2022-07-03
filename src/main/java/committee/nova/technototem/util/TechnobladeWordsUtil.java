package committee.nova.technototem.util;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Random;

public class TechnobladeWordsUtil {
    public static String genRandomTechnoWords() {
        Random random = new Random();
        List<String> words = ImmutableList.of( // Most from AutoTechno
                "Good luck, and may Techno's unmatched skill be with you",
                "Let's drop kick some children!",
                "Technoblade never dies!",
                "So, what do you guys know about anarchy?",
                "Blood for the Blood God",
                "In the name of techno",
                "This ones for technoblade",
                "Officer, I drop-kicked them in self defense!",
                "This is the second-worst thing to happen to these orphans.",
                "Sometimes it's tough being the best",
                "die nerd (/j)",
                "chin up king, your crown is falling",
                "gg e z",
                "good game",
                "Technoblade never dies",
                "as Sun Tzu wanted"
        );
        return words.get(random.nextInt(words.size()));
    }
}
