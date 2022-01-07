package fr.utbm.ap4b_project_fx.energySims.items.ressource;

public class Inventory {
    
    private final Resource[] resources;
    
    public Inventory(){
        this.resources = new Resource[ResourceType.values().length];
        int i = 0;
        for (ResourceType t:
             ResourceType.values()) {
            this.resources[i] = new Resource(0, t);
            i ++;
        }
        this.addResource(new Resource(30, ResourceType.IRON));
        this.addResource(new Resource(20,  ResourceType.COAL));
    }

    public Inventory(String string){
        String[] split = string.split(",");
        this.resources = new Resource[ResourceType.values().length];
        int i = 0;
        for (ResourceType t:
                ResourceType.values()) {
            this.resources[i] = new Resource(split[i]);
            i ++;
        }

    }

    public Resource[] getResources() {
        return resources;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        value.append(resources[0].toString());
        for (int i = 1; i < ResourceType.values().length; i++) {
            value.append(",").append(resources[i].toString());
        }
        return value.toString();
    }

    public boolean addResource(Resource resource){
        if (resource != null){
            return this.resources[resource.getType().ordinal()].addResource(resource.getAmount());
        }
        return true;

    }

    public boolean useResource(Resource resource){
        if (resource != null){
            return this.resources[resource.getType().ordinal()].addResource(- resource.getAmount());
        }
        return true;
    }


}
