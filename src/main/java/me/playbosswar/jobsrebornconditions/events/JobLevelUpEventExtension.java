package me.playbosswar.jobsrebornconditions.events;

import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import me.playbosswar.com.CommandTimerPlugin;
import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.api.events.EventExtension;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class JobLevelUpEventExtension extends EventExtension implements Listener {
    private final ConditionExtension extension;

    public JobLevelUpEventExtension(ConditionExtension extension) {
        this.extension = extension;
    }

    @Override
    public @NotNull String getEventName() {
        return "LEVEL_UP";
    }

    @Override
    public @NotNull String[] getEventDescription() {
        return new String[]{"Triggered when a player gets a new job level"};
    }

    @Override
    public ArrayList<NeededValue<?>> getReturnedValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("LEVEL", "Level", Integer.class, 0));
        values.add(new NeededValue<>("JOB_NAME", "Job name", String.class, ""));
        values.add(new NeededValue<>("NEW_PLAYER_TITLE", "New player title", String.class, ""));
        values.add(new NeededValue<>("OLD_PLAYER_TITLE", "Old player title", String.class, ""));
        values.add(new NeededValue<>("@PLAYER_UUID", "Player UUID", String.class, ""));

        return values;
    }

    @EventHandler
    public void onLevelUp(JobsLevelUpEvent e) {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("LEVEL", "Level", Integer.class, e.getLevel()));
        values.add(new NeededValue<>("JOB_NAME", "Job name", String.class, e.getJob().getName()));
        values.add(new NeededValue<>("NEW_PLAYER_TITLE", "New player title", String.class, e.getNewTitle().getName()));
        values.add(new NeededValue<>("OLD_PLAYER_TITLE", "Old player title", String.class, e.getOldTitle().getName()));
        values.add(new NeededValue<>("@PLAYER_UUID", "Player UUID", String.class, e.getPlayer().getUniqueId().toString()));
        CommandTimerPlugin.getInstance().getEventsManager().handleTriggeredEvent(extension, this, values);
    }
}
