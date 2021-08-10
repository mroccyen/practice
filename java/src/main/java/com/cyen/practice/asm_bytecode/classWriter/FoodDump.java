package com.cyen.practice.asm_bytecode.classWriter;

import org.objectweb.asm.*;

/**
 * @author qingshanpeng
 * @date 2021/8/10 15:01
 * @since 标果工厂-托曼尼
 */
public class FoodDump implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(52, ACC_PUBLIC + ACC_SUPER, "com/cyen/practice/asm_bytecode/classWriter/Food", null, "java/lang/Object", null);

		cw.visitSource("Food.java", null);

		{
			fv = cw.visitField(ACC_PRIVATE, "name", "Ljava/lang/String;", null, null);
			fv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitLdcInsn("default");
			mv.visitFieldInsn(PUTFIELD, "com/cyen/practice/asm_bytecode/classWriter/Food", "name", "Ljava/lang/String;");
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitInsn(RETURN);
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLocalVariable("this", "Lcom/cyen/practice/asm_bytecode/classWriter/Food;", null, l0, l3, 0);
			mv.visitMaxs(2, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/String;)V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitLdcInsn("default");
			mv.visitFieldInsn(PUTFIELD, "com/cyen/practice/asm_bytecode/classWriter/Food", "name", "Ljava/lang/String;");
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitInsn(RETURN);
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLocalVariable("this", "Lcom/cyen/practice/asm_bytecode/classWriter/Food;", null, l0, l3, 0);
			mv.visitLocalVariable("name", "Ljava/lang/String;", null, l0, l3, 1);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Ljava/lang/Integer;)V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitLdcInsn("default");
			mv.visitFieldInsn(PUTFIELD, "com/cyen/practice/asm_bytecode/classWriter/Food", "name", "Ljava/lang/String;");
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitInsn(RETURN);
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLocalVariable("this", "Lcom/cyen/practice/asm_bytecode/classWriter/Food;", null, l0, l3, 0);
			mv.visitLocalVariable("number", "Ljava/lang/Integer;", null, l0, l3, 1);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "sub", "()V", null, null);
			mv.visitCode();

			Label l0 = new Label();
			mv.visitLabel(l0);
			//加载1这个常量到操作数栈（操作数栈数量：1）
			mv.visitInsn(ICONST_1);
			//将1这个常量存储到本地变量栈中（操作数栈：0，本地变量栈：2）
			mv.visitVarInsn(ISTORE, 1);

			Label l1 = new Label();
			mv.visitLabel(l1);
			//加载2这个常量到操作数栈（操作数栈数量：1）
			mv.visitInsn(ICONST_2);
			//将2这个常量存储到本地变量栈中（操作数栈：0，本地变量栈：3）
			mv.visitVarInsn(ISTORE, 2);

			Label l2 = new Label();
			mv.visitLabel(l2);
			//加载常量2到操作数栈中
			mv.visitVarInsn(ILOAD, 2);
			//加载常量1到操作数栈中
			mv.visitVarInsn(ILOAD, 1);
			//取出2和1进行相减，结果保存到操作数栈中
			mv.visitInsn(ISUB);
			//保存计算结果到本地变量表的位置3，这时操作数栈数量为0
			mv.visitVarInsn(ISTORE, 3);

			Label l3 = new Label();
			mv.visitLabel(l3);
			//获取静态方法
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			//加载本地变量表位置为3的值到操作数栈中
			mv.visitVarInsn(ILOAD, 3);
			//调用方法，从操作数栈中取参数
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

			Label l4 = new Label();
			mv.visitLabel(l4);
			//方法返回
			mv.visitInsn(RETURN);

			Label l5 = new Label();
			mv.visitLabel(l5);
			//设置本地变量
			mv.visitLocalVariable("this", "Lcom/cyen/practice/asm_bytecode/classWriter/Food;", null, l0, l5, 0);
			mv.visitLocalVariable("a", "I", null, l1, l5, 1);
			mv.visitLocalVariable("b", "I", null, l2, l5, 2);
			mv.visitLocalVariable("c", "I", null, l3, l5, 3);

			//设置操作数栈大小和本地变量表大小
			mv.visitMaxs(2, 4);
			//结束
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "add", "()V", null, null);
			mv.visitCode();

			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitInsn(ICONST_2);
			mv.visitVarInsn(ISTORE, 1);

			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitInsn(ICONST_3);
			mv.visitVarInsn(ISTORE, 2);

			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitVarInsn(ILOAD, 1);
			mv.visitVarInsn(ILOAD, 2);
			mv.visitInsn(IADD);
			mv.visitVarInsn(ISTORE, 3);

			Label l4 = new Label();
			mv.visitLabel(l4);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(ILOAD, 3);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);

			Label l5 = new Label();
			mv.visitLabel(l5);
			mv.visitInsn(RETURN);

			Label l6 = new Label();
			mv.visitLabel(l6);
			mv.visitLocalVariable("this", "Lcom/cyen/practice/asm_bytecode/classWriter/Food;", null, l1, l6, 0);
			mv.visitLocalVariable("a", "I", null, l1, l6, 1);
			mv.visitLocalVariable("b", "I", null, l1, l6, 2);
			mv.visitLocalVariable("c", "I", null, l1, l6, 3);

			mv.visitMaxs(2, 4);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null);
			mv.visitCode();

			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitLdcInsn("stu");
			mv.visitFieldInsn(PUTFIELD, "com/cyen/practice/asm_bytecode/classWriter/Food", "name", "Ljava/lang/String;");

			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, "com/cyen/practice/asm_bytecode/classWriter/Food", "name", "Ljava/lang/String;");
			mv.visitInsn(ARETURN);

			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitLocalVariable("this", "Lcom/cyen/practice/asm_bytecode/classWriter/Food;", null, l0, l2, 0);
			mv.visitMaxs(2, 1);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}
