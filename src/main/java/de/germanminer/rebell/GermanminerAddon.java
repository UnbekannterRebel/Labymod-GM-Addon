package de.germanminer.rebell;

import net.labymod.api.LabyModAddon;
import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.labymod.utils.ServerData;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class GermanminerAddon extends LabyModAddon {

    private boolean badfrak = false;
    private boolean failblocker = false;

    final String prefix = (TextFormatting.DARK_RED + "[Rebells Kek Addon] ");
    final String failblockermsg = (prefix + TextFormatting.GOLD + "Der Fail blocker hat dich gerettet :D");



    @Override
    public void onEnable() {
        System.out.println("Germanminer LabyMod addon wurde geladen!");
        this.getApi().registerForgeListener(this);

        this.getApi().getEventManager().registerOnJoin(new Consumer<ServerData>() {

            public void accept(final ServerData serverData) {
                if(LabyMod.getInstance().getPlayerName() != null) {
                    LabyMod.getInstance().displayMessageInChat(TextFormatting.GOLD + "Du spielst aktuell auf dem server: " + serverData.getIp());

                }
            }

        });

        this.getApi().getEventManager().register(new MessageSendEvent() {

            public boolean onSend(final String message) {

                if(failblocker) {
                    final String message2;
                    message2 = message;
                    final String message3 = message2.toLowerCase();

                    if (message3.startsWith("7rs") || message3.startsWith("7h") || message3.startsWith("7m") || message3.startsWith("7menu")
                            || message3.startsWith("7vital") || message3.startsWith("7fett") || message3.startsWith("7ticket") || message3.startsWith("7sichern")
                            || message3.startsWith("7sicherung") || message3.startsWith("7baurechte") || message3.startsWith("7quests") || message3.startsWith("7ot")
                            || message3.startsWith("7weih")) { //Bei ende vom weihnachts event wieder entfernen!

                        LabyMod.getInstance().displayMessageInChat(failblockermsg);
                        return true;
                    }
                }
                return false;
            }

    });

        this.getApi().getEventManager().register(new MessageReceiveEvent()  {
            @Override
            public boolean onReceive(String s, String s1) {
                if (badfrak) {
                    if (s1.contains("[FUNK]")) {
                        if (s1.contains("ACHTUNG! Die gegnerische Fraktion versucht derzeit das Gebiet")) {
                            if (s1.contains("ACHTUNG! Die gegnerische Fraktion versucht derzeit das Gebiet News-Zentrale")) {
                                LabyMod.getInstance().displayMessageInChat(TextFormatting.DARK_RED + "Taxipunkt: News Zentrale");
                            } else {
                                if (s1.contains("ACHTUNG! Die gegnerische Fraktion versucht derzeit das Gebiet Skatepark Harrenheim")) {
                                    LabyMod.getInstance().displayMessageInChat(TextFormatting.DARK_RED + "Taxipunkt: Skatepark");
                                } else {
                                    if (s1.contains("ACHTUNG! Die gegnerische Fraktion versucht derzeit das Gebiet F+G Fabrikhalle")) {
                                        LabyMod.getInstance().displayMessageInChat(TextFormatting.DARK_RED + "Taxipunkt: Spring Spring Hopp");
                                    } else {
                                        if (s1.contains("ACHTUNG! Die gegnerische Fraktion versucht derzeit das Gebiet Alte Zollstelle")) {
                                            LabyMod.getInstance().displayMessageInChat(TextFormatting.DARK_RED + "Taxipunkt: Alte Zollstelle");
                                        } else {
                                            if (s1.contains("ACHTUNG! Die gegnerische Fraktion versucht derzeit das Gebiet Friedhof")) {
                                                LabyMod.getInstance().displayMessageInChat(TextFormatting.DARK_RED + "Taxipunkt: Friedhof");
                                            } else {
                                                if (s1.contains("ACHTUNG! Die gegnerische Fraktion versucht derzeit das Gebiet Tal")) {
                                                    LabyMod.getInstance().displayMessageInChat(TextFormatting.DARK_RED + "Taxipunkt: Medic Tal <3");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            //hier kommen halt andere funk nachrichten hin.
                        }
                    } else {
                        //hier wird alles bearbeitet, was kein funk ist.
                    }
            } else {
                    //wenn mann nicht im bad-frak modus ist
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
        this.failblocker = !this.getConfig().has("failblocker") || this.getConfig().get("failblocker").getAsBoolean();
        this.badfrak = !this.getConfig().has("badfrak") || this.getConfig().get("badfrak").getAsBoolean();
    }

    @Override
    protected void fillSettings(final List<SettingsElement> list) {
        final BooleanElement booleanElement = new BooleanElement("Fail blocker", new ControlElement.IconData(Material.BARRIER), new Consumer<Boolean>() {

            public void accept(final Boolean failblocker) {
                GermanminerAddon.this.failblocker = failblocker;

                GermanminerAddon.this.getConfig().addProperty("failblocker", failblocker);
                GermanminerAddon.this.saveConfig();
            }
        }, this.failblocker);

        final BooleanElement booleanElement1 = new BooleanElement("GW-Taxipunkt", new ControlElement.IconData(Material.DIAMOND_SWORD), new Consumer<Boolean>() {

            public void accept(Boolean badfrak) {
                GermanminerAddon.this.badfrak = badfrak;

                GermanminerAddon.this.getConfig().addProperty("badfrak", badfrak);
                GermanminerAddon.this.saveConfig();
            }
        }, this.badfrak);

        list.add(booleanElement);
        list.add(booleanElement1);
    }




}
