/*
 * Copyright 2016-2023 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.spotless.maven.npm;

import java.io.File;

import org.apache.maven.plugins.annotations.Parameter;

import com.diffplug.spotless.maven.FormatterStepConfig;
import com.diffplug.spotless.maven.FormatterStepFactory;
import com.diffplug.spotless.npm.NpmPathResolver;

public abstract class AbstractNpmFormatterStepFactory implements FormatterStepFactory {

	@Parameter
	private String npmExecutable;

	@Parameter
	private String npmrc;

	protected File npm(FormatterStepConfig stepConfig) {
		File npm = npmExecutable != null ? stepConfig.getFileLocator().locateFile(npmExecutable) : null;
		return npm;
	}

	protected File npmrc(FormatterStepConfig stepConfig) {
		File npmrc = this.npmrc != null ? stepConfig.getFileLocator().locateFile(this.npmrc) : null;
		return npmrc;
	}

	protected File buildDir(FormatterStepConfig stepConfig) {
		return stepConfig.getFileLocator().getBuildDir();
	}

	protected File baseDir(FormatterStepConfig stepConfig) {
		return stepConfig.getFileLocator().getBaseDir();
	}

	protected NpmPathResolver npmPathResolver(FormatterStepConfig stepConfig) {
		return new NpmPathResolver(npm(stepConfig), npmrc(stepConfig), baseDir(stepConfig));
	}
}
