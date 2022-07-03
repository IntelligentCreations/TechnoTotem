package committee.nova.technototem.data;

import com.redgrapefruit.itemnbt3.CustomData;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public class TotemOfTechnobladeData implements CustomData {
    private int kills;

    public TotemOfTechnobladeData() {
        this.kills = 0;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    @Override
    public @NotNull String getNbtCategory() {
        return "Kills";
    }

    @Override
    public void readNbt(@NotNull NbtCompound nbt) {
        kills = nbt.getInt("Kills");
    }

    @Override
    public void writeNbt(@NotNull NbtCompound nbt) {
        nbt.putInt("Kills", kills);
    }
}
