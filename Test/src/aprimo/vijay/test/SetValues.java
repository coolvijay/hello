package aprimo.vijay.test;

public class SetValues {

    static int rollbackPeriod;
    
    static int runPeriodInDays;

    public int getRollbackPeriod()
    {
        return rollbackPeriod;
    }

    public void setRollbackPeriod(int rollbackPeriod)
    {
        SetValues.rollbackPeriod = rollbackPeriod;
    }

    public int getRunPeriodInDays()
    {
        return runPeriodInDays;
    }

    public void setRunPeriodInDays(int runPeriodInDays)
    {
        SetValues.runPeriodInDays = runPeriodInDays;
    }
}
