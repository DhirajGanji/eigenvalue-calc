# eigenvalue-calc
Calculates eigenvalues for Hubbard Hamiltonian matrices. The user can input the 'n', 't', and 'u' values found in the Hubbard Hamiltonian equation.

Usage- Compile with-

javac -cp "jama.jar" Eigenvalue_calc.java

Run with-

java -cp "jama.jar" Eigenvalue_calc.java

User must enter an 'n' value, which is the number of electron sites in the desired Hubbard model. The computation time depends on the number of sites.
User must then enter the number of electrons in the sites, and then provide a choice for the 't' kinetic energy constant and the 'U' potential energy constant.

Future updates will include support for calculations involving the 'µ' chemical potential constant.

The JAMA library, which is public domain software built by the National Institute of Standards and Technology, is used to calculate the eigenvalues.
