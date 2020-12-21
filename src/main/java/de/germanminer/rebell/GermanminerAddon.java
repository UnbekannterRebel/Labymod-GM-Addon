package de.germanminer.rebell;

import net.labymod.api.LabyModAddon;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.labymod.utils.ServerData;

import java.util.List;

public class GermanminerAddon extends LabyModAddon {

    private boolean enabled = false;

    final String message4 = ("§c[Rebells Kek Addon]§6" + " Der Fail blocker hat dich gerettet :D");



    @Override
    public void onEnable() {
        System.out.println("Germanminer LabyMod addon wurde geladen!");
        this.getApi().registerForgeListener(this);

        this.getApi().getEventManager().registerOnJoin(new Consumer<ServerData>() {

            public void accept(final ServerData serverData) {
                if(LabyMod.getInstance().getPlayerName() != null) {
                    LabyMod.getInstance().displayMessageInChat("§6Du spielst aktuell auf dem server: " + serverData.getIp());

                }
            }

        });

        this.getApi().getEventManager().register(new MessageSendEvent() {

            public boolean onSend(final String message) {

                if(enabled) {
                    final String message2;
                    message2 = message;
                    final String message3 = message2.toLowerCase();

                    if (message3.startsWith("7rs") || message3.startsWith("7h") || message3.startsWith("7m") || message3.startsWith("7menu")
                            || message3.startsWith("7vital") || message3.startsWith("7fett") || message3.startsWith("7ticket") || message3.startsWith("7sichern")
                            || message3.startsWith("7sicherung") || message3.startsWith("7baurechte") || message3.startsWith("7quests")
                            || message3.startsWith("7weih")) { //Bei ende vom weihnachts event wieder entfernen!

                        LabyMod.getInstance().displayMessageInChat(message4);
                        return true;
                    }
                }
                return false;
            }

    });

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void loadConfig() {
        this.enabled = !this.getConfig().has("enabled") || this.getConfig().get("enabled").getAsBoolean();
    }

    @Override
    protected void fillSettings(final List<SettingsElement> list) {
        final BooleanElement booleanElement = new BooleanElement("Fail blocker", new ControlElement.IconData(Material.BARRIER), new Consumer<Boolean>() {

            public void accept(final Boolean enabled) {
                GermanminerAddon.this.enabled = enabled;

                GermanminerAddon.this.getConfig().addProperty("enabled", enabled);
                GermanminerAddon.this.saveConfig();
            }
        }, this.enabled);

        list.add(booleanElement);
    }




}
