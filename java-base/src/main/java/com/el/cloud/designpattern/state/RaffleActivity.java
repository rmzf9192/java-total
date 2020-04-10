package com.el.cloud.designpattern.state;

/**
 * @author Roman.Zhang
 * @date 2020/3/30
 * @description:
 */
public class RaffleActivity {
    State state;

    int count = 0;
    // 四个属性，表示四种状态
    State noRafflleState = new NoRaffleState(this);
    State canRaffleState = new CanRaffleState(this);

    State dispenseState =   new DispenseState(this);
    State dispensOutState = new DispenseOutState(this);

    public RaffleActivity(int count) {
        this.count = count;
        this.state = getNoRafflleState();
    }

    public void debuctMoney(){
        state.deductMoney();
    }

    public void raffle(){
        if(state.raffle()){
            state.dispensePrize();
        }
    }

    public int getCount() {
        int cruCount = count;
        count--;
        return cruCount;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getNoRafflleState() {
        return noRafflleState;
    }

    public void setNoRafflleState(State noRafflleState) {
        this.noRafflleState = noRafflleState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(State canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public void setDispenseState(State dispenseState) {
        this.dispenseState = dispenseState;
    }

    public State getDispensOutState() {
        return dispensOutState;
    }

    public void setDispensOutState(State dispensOutState) {
        this.dispensOutState = dispensOutState;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
