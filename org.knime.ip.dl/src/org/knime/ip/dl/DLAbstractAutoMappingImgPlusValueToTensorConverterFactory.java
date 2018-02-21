package org.knime.ip.dl;

import java.util.List;
import java.util.OptionalLong;

import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.ExtensibleUtilityFactory;
import org.knime.dl.core.DLTensorSpec;
import org.knime.dl.core.data.DLWritableBuffer;
import org.knime.dl.core.data.convert.DLAbstractTensorDataValueToTensorConverterFactory;
import org.knime.knip.base.data.img.ImgPlusValue;

/**
 * Handles the shape inference from data with auto-mapping.
 * 
 * @author Adrian Nembach, KNIME GmbH, Konstanz, Germany
 *
 * @param <O> type of the {@link DLWritableBuffer} the converters produced by this factory fill
 */
abstract class DLAbstractAutoMappingImgPlusValueToTensorConverterFactory <O extends DLWritableBuffer>
		extends DLAbstractTensorDataValueToTensorConverterFactory<ImgPlusValue, O> {

	@Override
	public final String getName() {
		return ((ExtensibleUtilityFactory) ImgPlusValue.UTILITY).getName() + " (Auto-mapping)";
	}

	@Override
	public final Class<ImgPlusValue> getSourceType() {
		return ImgPlusValue.class;
	}
	
	@Override
	public final OptionalLong getDestCount(final List<DataColumnSpec> spec) {
		return OptionalLong.empty();
	}
	
	@Override
	protected final long[] getDataShapeInternal(ImgPlusValue element, DLTensorSpec tensorSpec) {
		return DLKnipUtil.getShapeFromImg(element, tensorSpec);
	}
}
