package com.projectjava.demo;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.3.
 */
@SuppressWarnings("rawtypes")
public class Auth extends Contract {
    public static final String BINARY = "0x6080604052348015600f57600080fd5b50610bb88061001f6000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80633ffbd47f14610046578063544541b31461006257806371b8803714610092575b600080fd5b610060600480360381019061005b919061068c565b6100b0565b005b61007c6004803603810190610077919061068c565b610226565b604051610089919061071f565b60405180910390f35b61009a610461565b6040516100a791906107b9565b60405180910390f35b6001826040516100c09190610817565b908152602001604051809103902060009054906101000a900460ff161561011c576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101139061087a565b60405180910390fd5b6040518060400160405280838152602001828152506000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000190816101849190610ab0565b50602082015181600101908161019a9190610ab0565b50905050600180836040516101af9190610817565b908152602001604051809103902060006101000a81548160ff0219169083151502179055503373ffffffffffffffffffffffffffffffffffffffff167f48cac28ad4dc618e15f4c2dd5e97751182f166de97b25618318b2112aa951a2f8360405161021a91906107b9565b60405180910390a25050565b6000806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020604051806040016040529081600082018054610282906108c9565b80601f01602080910402602001604051908101604052809291908181526020018280546102ae906108c9565b80156102fb5780601f106102d0576101008083540402835291602001916102fb565b820191906000526020600020905b8154815290600101906020018083116102de57829003601f168201915b50505050508152602001600182018054610314906108c9565b80601f0160208091040260200160405190810160405280929190818152602001828054610340906108c9565b801561038d5780601f106103625761010080835404028352916020019161038d565b820191906000526020600020905b81548152906001019060200180831161037057829003601f168201915b5050505050815250509050836040516020016103a99190610817565b6040516020818303038152906040528051906020012081600001516040516020016103d49190610817565b604051602081830303815290604052805190602001201480156104465750826040516020016104039190610817565b60405160208183030381529060405280519060200120816020015160405160200161042e9190610817565b60405160208183030381529060405280519060200120145b1561045557600191505061045b565b60009150505b92915050565b60606000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000180546104af906108c9565b80601f01602080910402602001604051908101604052809291908181526020018280546104db906108c9565b80156105285780601f106104fd57610100808354040283529160200191610528565b820191906000526020600020905b81548152906001019060200180831161050b57829003601f168201915b5050505050905090565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b61059982610550565b810181811067ffffffffffffffff821117156105b8576105b7610561565b5b80604052505050565b60006105cb610532565b90506105d78282610590565b919050565b600067ffffffffffffffff8211156105f7576105f6610561565b5b61060082610550565b9050602081019050919050565b82818337600083830152505050565b600061062f61062a846105dc565b6105c1565b90508281526020810184848401111561064b5761064a61054b565b5b61065684828561060d565b509392505050565b600082601f83011261067357610672610546565b5b813561068384826020860161061c565b91505092915050565b600080604083850312156106a3576106a261053c565b5b600083013567ffffffffffffffff8111156106c1576106c0610541565b5b6106cd8582860161065e565b925050602083013567ffffffffffffffff8111156106ee576106ed610541565b5b6106fa8582860161065e565b9150509250929050565b60008115159050919050565b61071981610704565b82525050565b60006020820190506107346000830184610710565b92915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610774578082015181840152602081019050610759565b60008484015250505050565b600061078b8261073a565b6107958185610745565b93506107a5818560208601610756565b6107ae81610550565b840191505092915050565b600060208201905081810360008301526107d38184610780565b905092915050565b600081905092915050565b60006107f18261073a565b6107fb81856107db565b935061080b818560208601610756565b80840191505092915050565b600061082382846107e6565b915081905092915050565b7f456d61696c20697320616c726561647920726567697374657265642100000000600082015250565b6000610864601c83610745565b915061086f8261082e565b602082019050919050565b6000602082019050818103600083015261089381610857565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806108e157607f821691505b6020821081036108f4576108f361089a565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b60006008830261095c7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8261091f565b610966868361091f565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b60006109ad6109a86109a38461097e565b610988565b61097e565b9050919050565b6000819050919050565b6109c783610992565b6109db6109d3826109b4565b84845461092c565b825550505050565b600090565b6109f06109e3565b6109fb8184846109be565b505050565b5b81811015610a1f57610a146000826109e8565b600181019050610a01565b5050565b601f821115610a6457610a35816108fa565b610a3e8461090f565b81016020851015610a4d578190505b610a61610a598561090f565b830182610a00565b50505b505050565b600082821c905092915050565b6000610a8760001984600802610a69565b1980831691505092915050565b6000610aa08383610a76565b9150826002028217905092915050565b610ab98261073a565b67ffffffffffffffff811115610ad257610ad1610561565b5b610adc82546108c9565b610ae7828285610a23565b600060209050601f831160018114610b1a5760008415610b08578287015190505b610b128582610a94565b865550610b7a565b601f198416610b28866108fa565b60005b82811015610b5057848901518255600182019150602085019450602081019050610b2b565b86831015610b6d5784890151610b69601f891682610a76565b8355505b6001600288020188555050505b50505050505056fea26469706673582212206f21ea8b30639ed03bfa9f7009852ae2b1c5f5a8f39c8854ded3e20d26e4145264736f6c634300081c0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_AUTHENTICATE = "authenticate";

    public static final String FUNC_GETUSEREMAIL = "getUserEmail";

    public static final String FUNC_REGISTER = "register";

    public static final Event USERAUTHENTICATED_EVENT = new Event("UserAuthenticated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event USERREGISTERED_EVENT = new Event("UserRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected Auth(String contractAddress, Web3j web3j, Credentials credentials,
                   BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Auth(String contractAddress, Web3j web3j, Credentials credentials,
                   ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Auth(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                   BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Auth(String contractAddress, Web3j web3j, TransactionManager transactionManager,
                   ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<UserAuthenticatedEventResponse> getUserAuthenticatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(USERAUTHENTICATED_EVENT, transactionReceipt);
        ArrayList<UserAuthenticatedEventResponse> responses = new ArrayList<UserAuthenticatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UserAuthenticatedEventResponse typedResponse = new UserAuthenticatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.email = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UserAuthenticatedEventResponse getUserAuthenticatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(USERAUTHENTICATED_EVENT, log);
        UserAuthenticatedEventResponse typedResponse = new UserAuthenticatedEventResponse();
        typedResponse.log = log;
        typedResponse.userAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.email = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<UserAuthenticatedEventResponse> userAuthenticatedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUserAuthenticatedEventFromLog(log));
    }

    public Flowable<UserAuthenticatedEventResponse> userAuthenticatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(USERAUTHENTICATED_EVENT));
        return userAuthenticatedEventFlowable(filter);
    }

    public static List<UserRegisteredEventResponse> getUserRegisteredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(USERREGISTERED_EVENT, transactionReceipt);
        ArrayList<UserRegisteredEventResponse> responses = new ArrayList<UserRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.email = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UserRegisteredEventResponse getUserRegisteredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(USERREGISTERED_EVENT, log);
        UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
        typedResponse.log = log;
        typedResponse.userAddress = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.email = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<UserRegisteredEventResponse> userRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUserRegisteredEventFromLog(log));
    }

    public Flowable<UserRegisteredEventResponse> userRegisteredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(USERREGISTERED_EVENT));
        return userRegisteredEventFlowable(filter);
    }

    public RemoteFunctionCall<Boolean> authenticate(String _email, String _passwordHash) {
        final Function function = new Function(FUNC_AUTHENTICATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_email), 
                new org.web3j.abi.datatypes.Utf8String(_passwordHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> getUserEmail() {
        final Function function = new Function(FUNC_GETUSEREMAIL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> register(String _email, String _passwordHash) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_email), 
                new org.web3j.abi.datatypes.Utf8String(_passwordHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Auth load(String contractAddress, Web3j web3j, Credentials credentials,
                            BigInteger gasPrice, BigInteger gasLimit) {
        return new Auth(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Auth load(String contractAddress, Web3j web3j,
                            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auth(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Auth load(String contractAddress, Web3j web3j, Credentials credentials,
                            ContractGasProvider contractGasProvider) {
        return new Auth(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Auth load(String contractAddress, Web3j web3j,
                            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Auth(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Auth> deploy(Web3j web3j, Credentials credentials,
                                          ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Auth.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Auth> deploy(Web3j web3j, Credentials credentials,
                                          BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Auth.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Auth> deploy(Web3j web3j, TransactionManager transactionManager,
                                          ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Auth.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Auth> deploy(Web3j web3j, TransactionManager transactionManager,
                                          BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Auth.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }


    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class UserAuthenticatedEventResponse extends BaseEventResponse {
        public String userAddress;

        public String email;
    }

    public static class UserRegisteredEventResponse extends BaseEventResponse {
        public String userAddress;

        public String email;
    }
}
