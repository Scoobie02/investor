package pl.ws.investor.processor;

class InvestmentFactory {

    static Investment provideInvestmentProcessor(InvestmentStyle investmentStyle) throws UnsupportedOperationException {

        if(InvestmentStyle.SAFE.equals(investmentStyle)){
            return new SafeInvestment();
        } else if (InvestmentStyle.BALANCED.equals(investmentStyle)){
            return new BalancedInvestment();
        } else if(InvestmentStyle.AGGRESSIVE.equals(investmentStyle)){
            return new AggressiveInvestment();
        } else {
            throw new UnsupportedOperationException("Unsupported investmentStyle: "+ investmentStyle.name());
        }
    }
}
