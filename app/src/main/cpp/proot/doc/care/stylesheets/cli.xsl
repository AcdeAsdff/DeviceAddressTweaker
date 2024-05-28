<?xml version="1.0" encoding="UTF-8"?>

<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="text" />

  <xsl:template match="/">
    <xsl:text>/* This file is automatically generated from the documentation. EDIT AT YOUR OWN RISK. */

#ifndef CARE_CLI_H
#define CARE_CLI_H

#include "cli/cli.h"

#ifndef VERSION
#define VERSION "</xsl:text><xsl:value-of select="//version" /><xsl:text>"
#endif

#define CARE_MAX_SIZE 1024

</xsl:text>

<xsl:apply-templates select="//option_string[.='-c']" />
<xsl:apply-templates select="//option_string[.='-r']" />
<xsl:apply-templates select="//option_string[.='-p']" />
<xsl:apply-templates select="//option_string[.='-e']" />

    <xsl:apply-templates select="//option_group" mode="handlers" />
    <xsl:text>
static int pre_initialize_bindings(Tracee *, const Cli *, size_t, char *const *, size_t);
static int post_initialize_bindings(Tracee *, const Cli *, size_t, char *const *, size_t);
</xsl:text>
    <xsl:text>
static Cli care_cli = {
	.version  = VERSION,
	.name     = "care",
</xsl:text>
    <xsl:apply-templates select="//subtitle"/>
    <xsl:apply-templates select="//section[@names='synopsis']" />
    <xsl:apply-templates select="//section[@names='colophon']" />
    <xsl:apply-templates select="//section[@names='logo']" />
    <xsl:text>
	.pre_initialize_bindings  = pre_initialize_bindings,
	.post_initialize_bindings = post_initialize_bindings,

	.options = {
</xsl:text>
    <xsl:apply-templates select="//option_group" mode="options" />
  <xsl:text>	END_OF_OPTIONS,
	},
};

#endif /* CARE_CLI_H */
</xsl:text>
  </xsl:template>

  <!-- Constant string definitions -->

  <xsl:template match="subtitle">
    <xsl:text>	.subtitle = "</xsl:text>
    <xsl:value-of select="." />
    <xsl:text>",
</xsl:text>
  </xsl:template>

  <xsl:template match="section[@names='synopsis']">
    <xsl:text>	.synopsis = "</xsl:text>
    <xsl:value-of select="./paragraph" />
    <xsl:text>",
</xsl:text>
  </xsl:template>

  <xsl:template match="section[@names='colophon']">
    <xsl:text>	.colophon = "</xsl:text>
    <xsl:value-of select="./paragraph" />
    <xsl:text>",
</xsl:text>
    <xsl:text>	.logo = "\
</xsl:text>
    <xsl:value-of select="./literal_block" />
    <xsl:text>",
</xsl:text>
  </xsl:template>

  <!-- Recommanded options declarations -->

  <xsl:template match="option_string[.='-c']">
    <xsl:text>static char const *default_concealed_paths[] = {
</xsl:text>
    <xsl:apply-templates select="ancestor-or-self::option_list_item//list_item" />
    <xsl:text>	NULL,
};

</xsl:text>

  </xsl:template>

  <xsl:template match="option_string[.='-r']">
    <xsl:text>static char const *default_revealed_paths[] = {
</xsl:text>
    <xsl:apply-templates select="ancestor-or-self::option_list_item//list_item" />
    <xsl:text>	NULL,
};

</xsl:text>

  </xsl:template>

  <xsl:template match="option_string[.='-p']">
    <xsl:text>static char const *default_volatile_paths[] = {
</xsl:text>
    <xsl:apply-templates select="ancestor-or-self::option_list_item//list_item" />
    <xsl:text>	NULL,
};

</xsl:text>

  </xsl:template>

  <xsl:template match="option_string[.='-e']">
    <xsl:text>static char const *default_volatile_envars[] = {
</xsl:text>
    <xsl:apply-templates select="ancestor-or-self::option_list_item//list_item" />
    <xsl:text>	NULL,
};

</xsl:text>
  </xsl:template>

  <!-- Option declarations -->

  <xsl:template match="option_group" mode="options">
    <xsl:text>	{ .class = "</xsl:text>
    <xsl:value-of select="ancestor-or-self::section[1]/title" />
    <xsl:text>",
</xsl:text>
    <xsl:text>	  .arguments = {
</xsl:text>
    <xsl:apply-templates select="option" mode="options" />
    <xsl:text>		{ .name = NULL, .separator = '\0', .value = NULL } },
</xsl:text>
    <xsl:text>	  .handler = handle_option_</xsl:text>
    <xsl:value-of select="substring(option[1]/option_string, 2, 1)" />
    <xsl:text>,
</xsl:text>
    <xsl:text>	  .description = "</xsl:text>
    <xsl:apply-templates select="../description/paragraph[1]" mode="options" />
    <xsl:text>",
</xsl:text>
    <xsl:text>	  .detail = NULL,
	},
</xsl:text>
  </xsl:template>

  <xsl:template match="emphasis" mode="options">
    <xsl:text>*</xsl:text>
    <xsl:value-of select="." />
    <xsl:text>*</xsl:text>
  </xsl:template>

  <xsl:template match="paragraph">
      <xsl:apply-templates/>
    <xsl:text>

</xsl:text>
  </xsl:template>

  <!-- Option aliases declarations -->

  <xsl:template match="option" mode="options">
    <xsl:text>		{ </xsl:text>
    <xsl:text>.name = "</xsl:text>
    <xsl:value-of select="option_string" />
    <xsl:text>", .separator = '</xsl:text>
    <xsl:choose>
      <xsl:when test="option_argument">
	<xsl:value-of select="option_argument/@delimiter" />
	<xsl:text>', .value = "</xsl:text>
	<xsl:value-of select="option_argument" />
	<xsl:text>"</xsl:text>
      </xsl:when>
      <xsl:otherwise>
	<xsl:text>\0', .value = NULL</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:text> },
</xsl:text>
  </xsl:template>

  <!-- Handler declarations -->

  <xsl:template match="option_group" mode="handlers">
    <xsl:text>static int handle_option_</xsl:text>
    <xsl:value-of select="substring(option[1]/option_string, 2, 1)" />
    <xsl:text>(Tracee *tracee, const Cli *cli, const char *value);
</xsl:text>
  </xsl:template>

</xsl:transform>
