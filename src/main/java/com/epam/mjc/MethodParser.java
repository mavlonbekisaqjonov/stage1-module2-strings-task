package com.epam.mjc;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        StringTokenizer str = new StringTokenizer(signatureString, " ");

        String accessModifier = null;
        String returnType = "";
        String methodName = "";
        String parameters = "";

        // Extract Access Modifier (if present)
        if (str.hasMoreTokens()) {
            String firstToken = str.nextToken();
            if (firstToken.equals("public") || firstToken.equals("private") || firstToken.equals("protected")) {
                accessModifier = firstToken;
            } else {
                returnType = firstToken; // If no access modifier, it's the return type
            }
        }

        // Extract Return Type
        if (returnType.isEmpty() && str.hasMoreTokens()) {
            returnType = str.nextToken();
        }

        String remaining = "";

        if (str.hasMoreTokens()) {
            remaining = str.nextToken("").trim(); // Get remaining string
            remaining = remaining.replace("(", " ").replace(")", "").trim();
        }

        StringTokenizer str1 = new StringTokenizer(remaining);

        // Extract Method Name
        if (str1.hasMoreTokens()) {
            methodName = str1.nextToken();  // Fixed: using str1 instead of str to get the method name
        }

        if (str1.hasMoreTokens()) {
            parameters = str1.nextToken("").trim(); // Get remaining string
            parameters = parameters.replace("(", " ").replace(")", " ").trim();
        }

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String[] argPairs = parameters.split(", ");
        for (String arg : argPairs) {
            String[] parts = arg.trim().split(" "); // Split type and name
            if(parts.length==2){
                arguments.add(new MethodSignature.Argument(parts[0], parts[1])); // Create Argument object
            }
        }

        MethodSignature object = new MethodSignature(methodName, arguments);
        object.setAccessModifier(accessModifier);
        object.setReturnType(returnType);


        return object;
    }
}
