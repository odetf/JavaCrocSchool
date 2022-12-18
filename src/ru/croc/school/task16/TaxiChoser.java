package ru.croc.school.task16;

import java.util.ArrayList;
import java.util.List;

public class TaxiChoser {

    public List<Taxi> taxiBase;

    public TaxiChoser() {
        this.taxiBase = new ArrayList<>();
    }

    public Taxi sortTaxi(Taxi IdealTaxi) {
        taxiBase.sort((p1, p2) ->
                //проверяю, что для p1 и p2 равны типы такси и дополнительные сервисы (типа дет кресла)
                (p1.comparateTypeInfo(IdealTaxi) &
                        p2.comparateTypeInfo(IdealTaxi)) ?

                        //если они равны, то выдаю значение для сравнения расстояний для p1 и p2 (умноженное на (-1), чтобы
                        // меньшее расстояние считалось за большее
                        Double.compare(Math.sqrt(Math.pow(p1.getCoord1() - IdealTaxi.getCoord1(), 2) + Math.pow(p1.getCoord2() - IdealTaxi.getCoord2(), 2)),
                                Math.sqrt(Math.pow(p2.getCoord1() - IdealTaxi.getCoord1(), 2) + Math.pow(p2.getCoord2() - IdealTaxi.getCoord2(), 2))) :

                        p1.comparateTypeInfo(IdealTaxi) ? -1 :
                                p2.comparateTypeInfo(IdealTaxi) ? 1 : 0);

        return taxiBase.get(0);
    }

    public void addTaxi(Taxi taxi){
        this.taxiBase.add(taxi);
    }

    public boolean checkTaxi(Taxi IdealTaxi){
        return taxiBase.stream().anyMatch(taxi -> taxi.comparateTypeInfo(IdealTaxi));
    }

    public Taxi chooseDriver(Taxi taxi) throws NoSuchTaxiException {
        if (this.checkTaxi(taxi)){
            return this.sortTaxi(taxi);
        }
        else {
            throw new NoSuchTaxiException();
        }
    }
}
