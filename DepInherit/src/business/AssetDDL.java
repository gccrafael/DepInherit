
package business;

/**
 *
 * @author Rafael
 */
public class AssetDDL extends Asset{
    private double[] begbal, endbal, anndep;
    
    public AssetDDL() {
        super();
    }
    public AssetDDL(String name, double cost, double salvage, int life) {
        super(name, cost, salvage, life);
                calcDep();
    }
    private void calcDep()
    {
        try {   
            double straightDep = (super.getCost() - super.getSalvage()) / super.getLife();
            double doubledeclinerate = (1.0 / super.getLife()) * 2.0;
        
            begbal = new double[super.getLife()]; 
            endbal = new double[super.getLife()];
            anndep = new double[super.getLife()]; 
                
            begbal[0] = super.getCost(); // DDL start value        
            anndep[0] = super.getCost() * doubledeclinerate;
            endbal[0] = super.getCost() - anndep[0];
            for (int i = 1; i < super.getLife(); i++)
            {            
                    //DDL            
                if ((endbal[i-1] * doubledeclinerate) > straightDep) {
                    begbal[i] = endbal[i-1];
                    anndep[i] = begbal[i] * doubledeclinerate;
                    endbal[i] = begbal[i] - anndep[i];
                }
                else if ((endbal[i-1] * doubledeclinerate) < straightDep && 
                        (endbal[i-1] > super.getSalvage()) && 
                        (endbal[i-1] - straightDep > super.getSalvage())) {
                    begbal[i] = endbal[i-1];
                    anndep[i] = straightDep;
                    endbal[i] = begbal[i] - anndep[i];
                }
                else if ((endbal[i-1] * doubledeclinerate) < straightDep && 
                        (endbal[i-1] > super.getSalvage()) && 
                        (endbal[i-1] - straightDep < super.getSalvage())) {
                    begbal[i] = endbal[i-1];
                    anndep[i] = begbal[i] - super.getSalvage();
                    endbal[i] = super.getSalvage(); 
                }           
                else {
                    begbal[i] = endbal[i-1];
                    anndep[i] = 0;
                    endbal[i] = super.getSalvage();
                }
            }       
        super.setBuilt(true);
        } catch (Exception e){
            super.setBuilt(false);
        }       
    } 
    public double getAnnDep(int y)
    {         
        if (!super.getErrorMsg().isEmpty())
        {
                return -1;
        }    
        if (y < 0 || y > super.getLife())
        {
                return -1;
        }    
        return this.anndep[y];
                      
        
    }
    public double getBegBal(int y){
        return begbal[y];
    }
    public double getEndBal(int y){
        return endbal[y];
    }
    
    
}
