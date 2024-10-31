package me.playbosswar.jobsrebornconditions.conditions;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobsPlayer;
import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.conditionsengine.ConditionCompare;
import me.playbosswar.com.conditionsengine.conditions.ConditionHelpers;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HasSpecificJob implements ConditionRule {
    @Override
    public String getName() {
        return "HAS_SPECIFIC_JOB";
    }

    @Override
    public String getDescription() {
        return "Check the current job and level (use -1 to disable) of a user";
    }


    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");

        if (p == null) {
            return false;
        }

        String requiredJob = facts.get("required_job");
        JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(p.getUniqueId());

        if (jobsPlayer == null) {
            return false;
        }


        Job job = Jobs.getJob(requiredJob);
        boolean isInJob = jobsPlayer.isInJob(job);

        if (!isInJob) {
            return false;
        }

        int level = facts.get("level");

        if (level == -1) {
            return true;
        }

        ConditionCompare compare = facts.get("level_compare");
        int jobLevel = jobsPlayer.getJobProgression(job).getLevel();

        return ConditionHelpers.calculateConditionCompare(compare, jobLevel, level);
    }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("required_job", "Required job", String.class, ""));
        values.add(new NeededValue<>("level", "Level", Integer.class, -1));
        values.add(new NeededValue<>("level_compare", "Level compare", ConditionCompare.class, ConditionCompare.EQUAL));
        return values;
    }

    @Override
    public void execute(Facts facts) {
    }

    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}
