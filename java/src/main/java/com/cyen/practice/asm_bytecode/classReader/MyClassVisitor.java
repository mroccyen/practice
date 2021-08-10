package com.cyen.practice.asm_bytecode.classReader;

import org.objectweb.asm.*;

import java.io.IOException;

/**
 * @author qingshanpeng
 * @date 2021/8/9 13:52
 * @since 标果工厂-托曼尼
 */
public class MyClassVisitor extends ClassVisitor {

	public MyClassVisitor(int api) {
		super(api);
	}

	public MyClassVisitor(int api, ClassVisitor classVisitor) {
		super(api, classVisitor);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		System.out.println("class name:" + name);
		System.out.println("super class name:" + superName);
		System.out.println("class version:" + version);
		System.out.println("class access:" + access);
		System.out.println("class signature:" + signature);
		if (interfaces != null && interfaces.length > 0) {
			for (String str : interfaces) {
				System.out.println("implemented interface name:" + str);
			}
		}
	}

	@Override
	public void visitSource(String source, String debug) {
		super.visitSource(source, debug);
	}

	@Override
	public ModuleVisitor visitModule(String name, int access, String version) {
		return super.visitModule(name, access, version);
	}

	@Override
	public void visitNestHost(String nestHost) {
		super.visitNestHost(nestHost);
	}

	@Override
	public void visitOuterClass(String owner, String name, String descriptor) {
		super.visitOuterClass(owner, name, descriptor);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
		return super.visitAnnotation(descriptor, visible);
	}

	@Override
	public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
		return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
	}

	@Override
	public void visitAttribute(Attribute attribute) {
		super.visitAttribute(attribute);
	}

	@Override
	public void visitNestMember(String nestMember) {
		super.visitNestMember(nestMember);
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
		super.visitInnerClass(name, outerName, innerName, access);
	}

	@Override
	public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
		return super.visitField(access, name, descriptor, signature, value);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
		return super.visitMethod(access, name, descriptor, signature, exceptions);
	}

	@Override
	public void visitEnd() {
		super.visitEnd();
	}

	public static void main(String[] args) throws IOException {
		ClassReader classReader = new ClassReader("com.cyen.practice.asm_bytecode.classReader.Foo");
		classReader.accept(new MyClassVisitor(Opcodes.ASM7), 0);
	}
}
