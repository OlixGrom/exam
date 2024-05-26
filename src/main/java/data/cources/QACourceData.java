package data.cources;

public enum QACourceData implements ICourceData {
    QALead("QA Lead", CourcesData.Testing);

    private final String name;
    private final CourcesData courcesData;

    QACourceData(String name, CourcesData courcesData) {
        this.name = name;
        this.courcesData = courcesData;
    }

    public String getName() {
        return name;
    }

    public CourcesData getCourcesData() {
        return courcesData;
    }

}
