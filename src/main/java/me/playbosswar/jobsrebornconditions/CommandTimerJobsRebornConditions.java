package me.playbosswar.jobsrebornconditions;

import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.ConditionRules;
import me.playbosswar.com.api.events.EventExtension;
import me.playbosswar.jobsrebornconditions.conditions.HasSpecificJob;
import me.playbosswar.jobsrebornconditions.events.JobLevelUpEventExtension;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandTimerJobsRebornConditions extends ConditionExtension {
    ConditionRules rules = new ConditionRules();

    public CommandTimerJobsRebornConditions() {
        rules.register(new HasSpecificJob());
    }

    @Override
    public @NotNull String getConditionGroupName() {
        return "JOBSREBORN";
    }

    @Override
    public @NotNull String[] getDescription() {
        return new String[]{"&7Check against Jobs data and react to events related to Jobs"};
    }

    @Override
    public @NotNull String getAuthor() {
        return "PlayBossWar";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @NotNull String getRequiredPlugin() {
        return "Jobs";
    }

    public @NotNull ConditionRules getRules() {
        return rules;
    }

    @Override
    public ArrayList<EventExtension> getEvents() {
        ArrayList<EventExtension> events = new ArrayList<>();
        events.add(new JobLevelUpEventExtension(this));

        return events;
    }
}
