package de.villigi.plugbyte.api;

import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryApi {


    public void openMessageInventory(Player player) {

    }


    public void openMessageInventory(Player player, int page) {
        try {

            List<Message> messages = messageService.getMessages();
            int totalPages = (int) Math.ceil((double) messages.size() / BOOKS_PER_PAGE);
            if (page < 0 || page >= totalPages) return;

            Inventory inventory = Bukkit.createInventory(null, INVENTORY_SIZE, "Messages Page " + (page + 1));
            int start = page * BOOKS_PER_PAGE;
            int end = Math.min(start + BOOKS_PER_PAGE, messages.size());

            for (int i = start; i < end; i++) {
                Message message = messages.get(i);
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) book.getItemMeta();
                meta.setTitle(message.getPlaceholder());
                meta.addPage(message.getMessage());
                book.setItemMeta(meta);
                inventory.setItem(i - start, book);
            }

            if (page < totalPages - 1) {
                ItemStack nextPage = new ItemStack(Material.ARROW);
                ItemMeta meta = nextPage.getItemMeta();
                meta.setDisplayName("Next Page");
                nextPage.setItemMeta(meta);
                inventory.setItem(9*6 - 1, nextPage);
            }

            player.openInventory(inventory);
            playerPages.put(player.getName(), page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
