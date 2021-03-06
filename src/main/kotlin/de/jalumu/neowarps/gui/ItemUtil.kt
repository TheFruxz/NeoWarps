package de.jalumu.neowarps.gui

import de.jalumu.neowarps.util.Transmission
import eu.vironlab.simpleitemlib.SimpleItemBuilder
import eu.vironlab.simpleitemlib.SimpleItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryInteractEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.util.function.Consumer

class ItemUtil(val guiManager: GUIManager) {
    val placeholder: SimpleItemStack
    val privateWarps: SimpleItemStack
    val sharedWarps: SimpleItemStack
    val publicWarps: SimpleItemStack

    init {
        placeholder = SimpleItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE)
                .setDisplayName(
                        Transmission()
                                .color(ChatColor.GRAY)
                                .appendText(" ")
                                .transmissionContent
                )
                .setClickHandler { inventoryClickEvent: InventoryClickEvent ->
                    inventoryClickEvent.isCancelled = true
                    val player = inventoryClickEvent.whoClicked as Player
                    player.playSound(player.location, Sound.ENTITY_CAT_AMBIENT, 1f, 1f)
                }
                .build()
        privateWarps = SimpleItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE)
                .setDisplayName(
                        Transmission()
                                .color(ChatColor.LIGHT_PURPLE)
                                .appendText("Private Warps")
                                .transmissionContent
                )
                .setClickHandler { inventoryClickEvent: InventoryClickEvent ->
                    guiManager.openPrivateGui(inventoryClickEvent.whoClicked as Player)
                }
                .build()
        sharedWarps = SimpleItemBuilder(Material.YELLOW_STAINED_GLASS_PANE)
                .setDisplayName(
                        Transmission()
                                .color(ChatColor.YELLOW)
                                .appendText("Shared Warps")
                                .transmissionContent
                )
                .setClickHandler { inventoryClickEvent: InventoryClickEvent ->
                    inventoryClickEvent.isCancelled = true
                    guiManager.openSharedGui(inventoryClickEvent.whoClicked as Player)
                }
                .setInteractHandler { playerInteractEvent: PlayerInteractEvent ->
                    playerInteractEvent.isCancelled = true
                }
                .build()
        publicWarps = SimpleItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE)
                .setDisplayName(
                        Transmission()
                                .color(ChatColor.BLUE)
                                .appendText("Public Warps")
                                .transmissionContent
                )
                .setClickHandler { inventoryClickEvent: InventoryClickEvent ->
                    inventoryClickEvent.isCancelled = true
                    guiManager.openPublicGui(inventoryClickEvent.whoClicked as Player)
                }
                .build()
    }


}