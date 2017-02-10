package djomp.durabilitycheck;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by rs506 on 10/02/17.
 */
@Mod(modid = DurabilityCheck.modId, name = DurabilityCheck.name, version = DurabilityCheck.version, acceptedMinecraftVersions = "[1.9,1.10.2]")
public class DurabilityCheck {

    public static final String modId = "durabilitycheck";
    public static final String name = "Durability Check";
    public static final String version = "1.0.0";

    @Mod.Instance(modId)
    public static DurabilityCheck instance;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void checkItem(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack != null) {
            if (itemStack.getMaxDamage() != 0) {
                if (((double) itemStack.getItemDamage() / itemStack.getMaxDamage()) > 0.9) {
                    event.getEntityPlayer().addChatMessage(
                            new TextComponentString(
                                    TextFormatting.RED + "WARNING: " + itemStack.getDisplayName() + " has less than 10% durability left"
                            )
                    );
                }
            }
        }
    }

}
