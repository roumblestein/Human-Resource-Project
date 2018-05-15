package sample.InformationClasses;

/**
 * Created by Shpat on 2018-04-22.
 */
public class Skills {
    private String skill, level, skillCategory, experience, performance;

    public Skills(String skillCategory, String skill, String level, String experience, String performance){
        this.skill = skill;
        this.level = level;
        this.skillCategory = skillCategory;
        this.experience = experience;
        this.performance = performance;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}
