package ai.nightfall.scan.model;

/**
 * A modifier that is used to decide when a finding should be surfaced in the context of a detection rule.
 *
 * <p>When <code>ALL</code> is specified, all detectors in a detection rule must trigger a match in order for the
 * finding to be reported. This is the equivalent of a logical "AND" operator.
 *
 * <p>When <code>ANY</code> is specified, only one of the detectors in a detection rule must trigger a match in order
 * for the finding to be reported. This is the equivalent of a logical "OR" operator.
 */
public enum LogicalOp {
    ANY,
    ALL
}
