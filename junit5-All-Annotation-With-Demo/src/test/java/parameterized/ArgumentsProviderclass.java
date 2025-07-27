package parameterized;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ArgumentsProviderclass implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.arguments(0,1),
                Arguments.arguments(1,1),
                Arguments.arguments(2,2),
                Arguments.arguments(3,6)

        );
    }
}
