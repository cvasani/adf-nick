package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import com.packt.jdeveloper.cookbook.shared.bc.utilviews.ApplicationModulePoolStatisticsRowImpl;

import java.util.Enumeration;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Session;
import oracle.jbo.Variable;
import oracle.jbo.VariableValueManager;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaHints;

import oracle.jbo.common.ampool.ApplicationPool;
import oracle.jbo.common.ampool.PoolMgr;
import oracle.jbo.common.ampool.Statistics;
import oracle.jbo.server.ApplicationModuleImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import oracle.jbo.domain.Number;

public class ExtApplicationModuleImpl extends ApplicationModuleImpl implements ExtApplicationModule {

    // create an ADFLogger
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(ExtApplicationModuleImpl.class);

    private static int AUTHORITY_LEVEL_MINIMAL = 1;
    private static int AUTHORITY_LEVEL_NORMAL = 2;
    private static int AUTHORITY_LEVEL_HIGH = 3;
    private static final String CUSTOM_DATA_PASSIVATION_ID = "customDataPassivationId";

    public ExtApplicationModuleImpl() {
        super();
        // log a trace
        LOGGER.info("ExtApplicationModuleImpl was constructed");
    }

    public Object getCustomData(String key) {
        // base class returns no custom data
        return null;
    }


    /**
     * Recipe: Creating and using generic extension interfaces.
     *
     * @return, the user language code.
     */
    public int getUserAuthorityLevel() {
        // return some user authority level, based on
        // the user's name
        //        this.get
        return ("anonymous".equalsIgnoreCase(this.getUserPrincipalName())) ? AUTHORITY_LEVEL_MINIMAL :
               AUTHORITY_LEVEL_NORMAL;
    }

    @Override
    protected void passivateState(Document document, Element element) {
        // TODO Implement this method
        super.passivateState(document, element);

        // begin custom data passivation: returns a
        // list of the custom data passivation identifiers
        String[] passivationIds = onStartPassivation();
        // process all passivation identifiers
        for (String passivationId : passivationIds) {
            // check for valid identifier
            if (passivationId != null && passivationId.trim().length() > 0) {
                // passivate custom data: returns
                // the passivation data
                String passivationValue = onPassivate(passivationId);
                // check for valid passivation data
                if (passivationValue != null && passivationValue.length() > 0) {
                    // create a new text node in the
                    // passivation XML
                    Node node = document.createElement(passivationId);
                    Node cNode = document.createTextNode(passivationValue);
                    node.appendChild(cNode);
                    // add the passivation node to the
                    // parent element
                    element.appendChild(node);
                }
            }
        }
        // inform end of custom data passivation
        onEndPassivation();
    }

    @Override
    protected void activateState(Element element) {
        // TODO Implement this method
        super.activateState(element);

        // check for element to activate
        if (element != null) {
            // begin custom data activation: returns a
            // list of the custom data activation identifiers
            String[] activationIds = onStartActivation();
            // process all activation identifiers
            for (String activationId : activationIds) {
                // check for valid identifier
                if (activationId != null && activationId.trim().length() > 0) {
                    // get nodes from XML for the specific
                    // activation identifier
                    NodeList nl = element.getElementsByTagName(activationId);
                    // if it was found in the activation data
                    if (nl != null) {
                        // activate each node
                        for (int n = 0, length = nl.getLength(); n < length; n++) {
                            Node child = nl.item(n).getFirstChild();
                            if (child != null) {
                                // do the actual custom data
                                // activation
                                onActivate(activationId, child.getNodeValue().toString());
                                break;
                            }
                        }
                    }
                }
            }
            // inform end of custom data activation
            onEndActivation();
        }
    }


    private String[] getActivationPassivationIds() {
        // return the passivation/activation identifiers
        return new String[] { CUSTOM_DATA_PASSIVATION_ID };
    }


    protected String[] onStartPassivation() {
        // default implementation: no passivation ids
        // are defined
        // return the passivation identifiers
        return getActivationPassivationIds();
        //        return new String[] { };
    }

    protected String onPassivate(String passivationId) {
        // default implementation: passivates nothing
        String passivationData = null;
        // passivate this application module's
        // custom data only
        if (CUSTOM_DATA_PASSIVATION_ID.equals(passivationId)) {
            // return the custom data from the Application
            // Module session user data
            passivationData = (String) getSession().getUserData().get(CUSTOM_DATA_PASSIVATION_ID);
        }
        return passivationData;

        //        return null;
    }

    protected void onEndPassivation() {
        // default implementation: does nothing

    }

    protected String[] onStartActivation() {
        // default implementation: no activation ids
        // are defined
        // return the activation identifiers
        return getActivationPassivationIds();
        //        return new String[] { };
    }

    protected void onActivate(String activationId, String activationData) {
        // default implementation: activates nothing
        // activate this application module's custom data only
        if (CUSTOM_DATA_PASSIVATION_ID.equals(activationId)) {
            // add custom data to the Application
            // Module's session
            getSession().getUserData().put(CUSTOM_DATA_PASSIVATION_ID, activationData);
        }
    }

    /**
     * Recipe: Restoring the current row after a transaction rollback.
     *
     * @param session, the oracle.jbo.Session.
     */

    @Override
    protected void prepareSession(Session session) {
        // TODO Implement this method
        super.prepareSession(session);
        // framework processing
        super.prepareSession(session);
        // do not clear the cache after a rollback
        getDBTransaction().setClearCacheOnRollback(false);

        getSession().getUserData().put(CUSTOM_DATA_PASSIVATION_ID, "Some custom data");

    }

    protected void onEndActivation() {
        // default implementation: does nothing
    }

    public ExtViewObjectImpl getApplicationModulePoolStatistics() {
        return (ExtViewObjectImpl) findViewObject("ApplicationModulePoolStatistics");
    }


    //    Getting Statistics and insert into ApplicationModulePoolStatistics PVO
    public void getAMPoolStatistics() {
        // get the pool manager
        PoolMgr poolMgr = PoolMgr.getInstance();
        // get the pools managed
        Enumeration keys = poolMgr.getResourcePoolKeys();
        // iterate over pools
        while (keys != null && keys.hasMoreElements()) {
            // get pool name
            String poolname = (String) keys.nextElement();
            // get the pool
            ApplicationPool pool = (ApplicationPool) poolMgr.getResourcePool(poolname);
            // get the pool statistics
            Statistics statistics = pool.getStatistics();
            // get and populate pool statistics view object
            ExtViewObjectImpl amPoolStatistics = getApplicationModulePoolStatistics();
            if (amPoolStatistics !=
                null) {
                // empty the statistics
                amPoolStatistics.executeEmptyRowSet();
                // create and fill a new statistics row
                ApplicationModulePoolStatisticsRowImpl poolInfo =
(ApplicationModulePoolStatisticsRowImpl) amPoolStatistics.createRow();
                poolInfo.setPoolName(pool.getName());
                poolInfo.setApplicationModuleClass(pool.getApplicationModuleClass());
                poolInfo.setAvailableInstanceCount(new Number(pool.getAvailableInstanceCount()));
                poolInfo.setInitPoolSize(new Number(pool.getInitPoolSize()));
                poolInfo.setInstanceCount(new Number(pool.getInstanceCount()));
                poolInfo.setMaxPoolSize(new Number(pool.getMaxPoolSize()));
                poolInfo.setNumOfStateActivations(new Number(statistics.mNumOfStateActivations));
                poolInfo.setNumOfStatePassivations(new Number(statistics.mNumOfStatePassivations));
                poolInfo.setNumOfInstancesReused(new Number(statistics.mNumOfInstancesReused));
                poolInfo.setRefInstancesRecycled(new Number(statistics.mNumOfReferencedInstancesRecycled));
                poolInfo.setUnrefInstancesRecycled(new Number(statistics.mNumOfUnreferencedInstancesRecycled));
                poolInfo.setReferencedApplicationModules(new Number(statistics.mReferencedApplicationModules));
                poolInfo.setNumOfSessions(new Number(statistics.mNumOfSessions));
                poolInfo.setAvgNumOfSessionsRefState(new Number(statistics.mAvgNumOfSessionsReferencingState));

                // add the statistics
                amPoolStatistics.insertRow(poolInfo);
            }
        }
    }


    public void resetCriteriaValues(ViewCriteria vc) {
        // reset automatic execution
        vc.setProperty(ViewCriteriaHints.CRITERIA_AUTO_EXECUTE, false);
        // reset view criteria variables
        VariableValueManager vvm = vc.ensureVariableManager();
        Variable[] variables = vvm.getVariables();
        for (Variable variable : variables) {
            vvm.setVariableValue(variable, null);
        }
        // reset view criteria
        vc.resetCriteria();
        vc.saveState();
    }

}
