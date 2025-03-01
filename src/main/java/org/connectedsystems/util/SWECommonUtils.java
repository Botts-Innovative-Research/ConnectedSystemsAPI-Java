package org.connectedsystems.util;

import com.google.common.collect.ImmutableSet;
import org.vast.swe.IComponentFilter;
import org.vast.swe.SWEConstants;

import java.util.Set;

public class SWECommonUtils {
    public static final Set<String> OM_COMPONENTS_DEF = ImmutableSet.of(
            SWEConstants.DEF_PHENOMENON_TIME,
            SWEConstants.DEF_SAMPLING_TIME,
            SWEConstants.DEF_FORECAST_TIME,
            SWEConstants.DEF_RUN_TIME
    );
    /*
     * Filter to skip properties already provided by O&M from result
     * e.g. time stamp and feature of interest ID
     */
    public static final IComponentFilter OM_COMPONENTS_FILTER = comp -> {
        var def = comp.getDefinition();
        return comp.getParent() != null && !OM_COMPONENTS_DEF.contains(def);
    };

    private SWECommonUtils() {
        // prevent instantiation
    }
}
